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

import java.time.Duration;
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

    private final WikipediaService wikipediaService;

    // Define a threshold for relevant documents. If fewer than this, try Wikipedia.
    private static final int MIN_DOCUMENTS_THRESHOLD = 10; // You can adjust this value


    @Autowired // Spring Boot will find these beans
    public ChatServiceImpl(ChatClient.Builder chatClientBuilder, VectorStore vectorStore, ChatMemory chatMemory, WikipediaService wikipediaService) {
        // Initialize ChatMemory (e.g., in-memory window of 20 messages)
        this.chatMemory = chatMemory;
        // Configure the ChatClient with the MessageChatMemoryAdvisor using its builder
      /*  this.chatClient = chatClientBuilder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(this.chatMemory).build())
                .build();*/
        this.chatClient = chatClientBuilder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(this.chatMemory) // ChatMemory is the first parameter to builder
                        .conversationId(CONVERSATION_ID_VALUE) // Set conversation ID using a separate method
                        .build())
                .build();
        this.vectorStore = vectorStore;
        this.wikipediaService = wikipediaService;
    }
    // This is the prompt template that will be used for RAG.
    // It instructs the AI to use the provided context.
    @Value("${app.rag.prompt-template:}") // Can be configured in application.properties or defaulted
    private String ragPromptTemplate ;/*= """
            You are a helpful cricket expert chatbot.
            Use the following pieces of context to answer the user's question.
            If you don't know the answer, just say that you don't know, don't try to make up an answer.
            ---------------------
            Context:
            {context}
            ---------------------
            Question: {question}
            """;*/


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

    /*This is for synchronus call*/
    @Override
    public String chat(String message) {
        System.out.println("Received prompt: " + message);

        // 1. Initial Retrieval from ChromaDB
        // Use SearchRequest.builder() to create a SearchRequest
        List<Document> documents = vectorStore.similaritySearch(SearchRequest.builder().query(message).topK(5).build());
        System.out.println("Retrieved " + documents.size() + " relevant documents for prompt: '" + message + "' from ChromaDB.");

        // 2. Conditional Dynamic Ingestion from Wikipedia
        // Check if retrieved documents are insufficient (e.g., empty or below a threshold)
        String ingestionStatus = "";
        if (documents.size() < MIN_DOCUMENTS_THRESHOLD) {
            System.out.println("Insufficient documents found (" + documents.size() + "). Attempting to ingest from Wikipedia...");
            ingestionStatus = wikipediaService.searchAndIngest(message);
            System.out.println("Wikipedia ingestion status: " + ingestionStatus);

            // Re-retrieve documents after potential ingestion
            // Use SearchRequest.builder() to create a SearchRequest
            documents = vectorStore.similaritySearch(SearchRequest.builder().query(message).topK(5).build());
            System.out.println("Re-retrieved " + documents.size() + " relevant documents after Wikipedia ingestion.");
        }

        // 3. Prepare Prompt with Context (RAG)
        String context = "";
        if (!documents.isEmpty()) {
            context = documents.stream()
                    .map(doc -> doc.getFormattedContent() + " (Source: " + doc.getMetadata().getOrDefault("source", "unknown") + ")")
                    .collect(Collectors.joining("\n\n"));
        }


        Map<String, Object> modelVariables = new HashMap<>();

        if (!context.isEmpty()) {
            modelVariables.put("context", context);
            modelVariables.put("question", message);
            System.out.println("Sending prompt to LLM with RAG context.");
        } else {
            // Fallback: Ask the LLM directly if no relevant context was found even after dynamic ingestion
            modelVariables.put("question", message);
            System.out.println("No relevant documents found even after Wikipedia ingestion. Asking LLM directly without specific RAG context.");
        }
        // adding wiki status to user will know 
        modelVariables.put("wikipedia_status",ingestionStatus);
        PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);

        // 4. Create the UserMessage from the PromptTemplate
        // And now, add the conversation ID directly to the UserMessage's metadata
        // 4. Create the UserMessage using its builder, including metadata
        // CORRECTED: Use UserMessage.builder() for metadata
        UserMessage userMessage = UserMessage.builder()
                .text(promptTemplate.createMessage(modelVariables).getText()) // Set text content
                .metadata(Map.of(CONVERSATION_ID, CONVERSATION_ID_VALUE)) // Set metadata using builder
                .build(); // Build the UserMessage

        // 5. Build the Prompt object with the user message and the conversation ID parameter
        Prompt ragPrompt = new Prompt(userMessage);

        // 5. Send the RAG-enabled prompt to the ChatClient and get the content
        // Corrected: Simpler call that directly returns ChatResponse from Prompt
        ChatResponse chatResponse = chatClient.prompt(ragPrompt).call().chatResponse(); // Call returns ChatResponse
        String aiResponse = chatResponse.getResult().getOutput().getText(); // Get content from ChatResponse

        return aiResponse;
    }

    /*This is for asynchronous */
   /* @Override
    public Flux<String> chat(String message) {
        System.out.println("Received prompt: " + message);

        // 1. Asynchronously fetch and ingest Wikipedia content
        Mono<String> wikipediaIngestionMono = wikipediaService.searchAndIngest(message)
                .onErrorResume(e -> {
                    System.err.println("Error during Wikipedia ingestion (handled reactively): " + e.getMessage());
                    return Mono.just("Failed to fetch Wikipedia content due to an error: " + e.getMessage());
                })
                .timeout(Duration.ofSeconds(15), Mono.just("Wikipedia ingestion timed out.")) // Add a timeout
                .defaultIfEmpty("No specific Wikipedia status available."); // Default if Mono completes empty

        // 2. Combine Wikipedia ingestion status with the main RAG flow
        // Use flatMap to ensure Wikipedia ingestion completes before proceeding to vector store search and LLM call
        return wikipediaIngestionMono.flatMapMany(wikipediaStatus -> { // Use flatMapMany if the final result is a Flux

            // 3. Retrieve documents from vector store (this is also typically blocking unless your VectorStore impl is reactive)
            // If VectorStore.similaritySearch is blocking, it should ideally be run on a Schedulers.boundedElastic() thread.
            List<Document> documents = vectorStore.similaritySearch(SearchRequest.builder().query(message).topK(5).build());

            // Extract sources from retrieved documents
            List<String> sources = documents.stream()
                    .map(doc -> doc.getMetadata().getOrDefault("source", "Unknown Source").toString())
                    .distinct()
                    .collect(Collectors.toList());

            // 4. Prepare the prompt template with context
            PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
            Map<String, Object> promptModel = Map.of(
                    "context", documents.stream().map(Document::getFormattedContent).toList(),
                    "question", message,
                    "wikipedia_status", wikipediaStatus // Pass status to prompt
            );
            UserMessage userMessage = UserMessage.builder()
                    .text(promptTemplate.createMessage(promptModel).getText()) // Set text content
                    .metadata(Map.of(CONVERSATION_ID, CONVERSATION_ID_VALUE)) // Set metadata using builder
                    .build(); // Build the UserMessage

            // 5. Build the Prompt object with the user message and the conversation ID parameter
            Prompt ragPrompt = new Prompt(userMessage);
            // 5. Stream LLM response
            return chatClient.prompt(ragPrompt)
                    .stream()
                    .content(); // Returns Flux<String>
        });
    }*/
}
