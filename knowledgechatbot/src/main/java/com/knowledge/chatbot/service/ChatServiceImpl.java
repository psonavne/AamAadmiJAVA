package com.knowledge.chatbot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.openai.llm.OpenAiClient;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@Service
public class ChatServiceImpl implements ChatService{

    public static String LIMIT_CHARACTER = "and limit my response in 50 characters";

    private static final Logger logger = LoggerFactory.getLogger(ChatService.class); // Added logger

    @Autowired
    OpenAiClient aiClient;

    private final ChatClient chatClient;
    private final VectorStore vectorStore;
    private final ChatMemory chatMemory;
    private static final String CONVERSATION_ID_VALUE = "cricket-chat-session";

    @Autowired // Spring Boot will find these beans
    public ChatServiceImpl(ChatClient.Builder chatClientBuilder, VectorStore vectorStore, ChatMemory chatMemory) {
        // Initialize ChatMemory (e.g., in-memory window of 20 messages)
        this.chatMemory = chatMemory;

        // Configure the ChatClient with the MessageChatMemoryAdvisor using its builder
        this.chatClient = chatClientBuilder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(this.chatMemory).build())
                .build();
        this.vectorStore = vectorStore;
    }
    // This is the prompt template that will be used for RAG.
    // It instructs the AI to use the provided context.
    @Value("${app.rag.prompt-template:}") // Can be configured in application.properties or defaulted
    private String ragPromptTemplate = """
            You are a helpful cricket expert chatbot.
            Use the following pieces of context to answer the user's question.
            If you don't know the answer, just say that you don't know, don't try to make up an answer.
            ---------------------
            Context:
            {context}
            ---------------------
            Question: {question}
            """;


    @Override
    public String getResponse(String prompt) {
        aiClient.setModel("gpt-4");
        aiClient.setTemperature(0D);
        String response = aiClient.generate(prompt + LIMIT_CHARACTER);
        System.out.println("AI Response: " + response);
        return response;
    }

   /* @Override
    public String getChatResponse(String prompt) {
        // Send the user's prompt to the OpenAI ChatClient and get the content of the response
        aiClient.setModel("gpt-4");
        aiClient.setTemperature(0D);
        String response = aiClient.generate(prompt + LIMIT_CHARACTER);
        System.out.println("AI Response: " + response);
        return response;
    }*/

    @Override
    public String getChatResponse(String prompt) {
        logger.info("Received prompt: {}", prompt);

        // 1. Retrieve relevant documents from the vector store
        List<Document> relevantDocuments = vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(prompt)
                        .topK(4)
                        .build()
        );

        logger.info("Retrieved {} relevant documents for prompt: {}", relevantDocuments.size(), prompt);

        // 2. Extract content from relevant documents to form the context
        String context = relevantDocuments.stream()
                .map(doc -> {
                    // Try getContents().get(0) first, then getFormattedContent()
                    if (doc.getFormattedContent() != null && !doc.getFormattedContent().isEmpty()) {
                        return doc.getFormattedContent();
                    }
                    return doc.getFormattedContent();
                })
                .collect(Collectors.joining("\n\n"));

        if (context.isEmpty()) {
            logger.warn("No context found for prompt: {}", prompt);
        } else {
            logger.debug("Context for RAG: \n{}", context);
        }

        // 3. Create a map for the prompt template variables
        Map<String, Object> modelParameters = new HashMap<>();
        modelParameters.put("context", context);
        modelParameters.put("question", prompt);


        // 4. Create a PromptTemplate and fill it with context and question
        PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);

        // 4. Create the UserMessage from the PromptTemplate
        // And now, add the conversation ID directly to the UserMessage's metadata
        // 4. Create the UserMessage using its builder, including metadata
        // CORRECTED: Use UserMessage.builder() for metadata
        UserMessage userMessage = UserMessage.builder()
                .text(promptTemplate.createMessage(modelParameters).getText()) // Set text content
                .metadata(Map.of(CONVERSATION_ID, CONVERSATION_ID_VALUE)) // Set metadata using builder
                .build(); // Build the UserMessage

        // 5. Build the Prompt object with the user message and the conversation ID parameter
        Prompt ragPrompt = new Prompt(userMessage);

        // 5. Send the RAG-enabled prompt to the ChatClient and get the content
        // Corrected: Simpler call that directly returns ChatResponse from Prompt
        ChatResponse chatResponse = chatClient.prompt(ragPrompt).call().chatResponse(); // Call returns ChatResponse
        String aiResponse = chatResponse.getResult().getOutput().getText(); // Get content from ChatResponse

        logger.info("AI Response: {}", aiResponse);
        return aiResponse;
    }

}
