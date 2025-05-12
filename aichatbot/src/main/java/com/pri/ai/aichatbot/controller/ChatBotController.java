package com.pri.ai.aichatbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import java.util.List;

import com.theokanning.openai.completion.CompletionRequest; // Correct import
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ChatBotController {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    private final JdbcTemplate jdbcTemplate;

    public ChatBotController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/api/chatbot")
    public Map<String, Object> handlePrompt(@RequestBody String prompt) {
        OpenAiService openAiService = new OpenAiService(openaiApiKey);

        // 1. Interact with OpenAI to generate SQL
        String sqlQuery = generateSqlQuery(openAiService, prompt);
        System.out.println("Query will execute in DB : " + sqlQuery);
        List<Map<String, Object>> queryResults = null;
        if (sqlQuery != null && !sqlQuery.trim().isEmpty()) {
            try {
                // 2. Connect to Oracle and execute the query
                queryResults = jdbcTemplate.queryForList(sqlQuery);
            } catch (Exception e) {
                return Map.of("error", "Error executing SQL query: " + e.getMessage());
            }
        } else {
            return Map.of("response", "Could not generate a valid SQL query from the prompt.");
        }

        // 3. Format and return the results
        return Map.of("results", queryResults);
    }

    private String generateSqlQuery(OpenAiService openAiService, String prompt) {
        String schemaContext = "Here is the schema of the Oracle database tables you can query:\n" +
                "User_table (user_id VARCHAR2 PRIMARY KEY, user_name VARCHAR2, user_email VARCHAR2, user_status VARCHAR2)\n" +
                "Product_REF (Product_id VARCHAR2 PRIMARY KEY, Product_Name VARCHAR2)\n" +
                "Order_details (Order_id VARCHAR2 PRIMARY KEY, Order_type VARCHAR2, Ordered_By VARCHAR2, Order_date DATE)\n" +
                "Product_Details (Product_detail_id VARCHAR2 PRIMARY KEY, Product_id VARCHAR2 FK to Product_REF, Order_Id VARCHAR2 FK to Order_details, Product_status VARCHAR2, Product_modified_date DATE, modified_by VARCHAR2)\n\n" +
                "When comparing text values, please perform a case-insensitive comparison. If the user's query seems to be looking for a partial match (e.g., 'orders by Pritam' when the user ID is 'pritam12'), " +
                "use the LIKE operator in conjunction with the LOWER() function on both the column and the search term. For example: LOWER(Ordered_By) LIKE LOWER('%Pritam%').\n\n" +
                "Use this schema and the above instructions to generate a SQL query for the following natural language prompt:\n\n";


        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct") // Or another suitable model
                .prompt(schemaContext + prompt + "\n\nSQL:")
                .maxTokens(200) // Adjust as needed
                .temperature(0.2) // Adjust for accuracy
                .n(1)
                .stop(List.of(";"))
                .build();

        return openAiService.createCompletion(completionRequest).getChoices().stream()
                .findFirst()
                .map(choice -> choice.getText().trim())
                .orElse(null);
    }
}