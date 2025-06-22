package com.knowledge.chatbot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Iterator;

@Service
public class WikipediaServiceImpl implements WikipediaService {

    private final WebClient webClient;
    private final DocumentIngestionService ingestionService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public WikipediaServiceImpl(WebClient.Builder webClientBuilder, DocumentIngestionService ingestionService) {
        // Wikipedia API base URL
        this.webClient = webClientBuilder.baseUrl("https://en.wikipedia.org/w/api.php").build();
        this.ingestionService = ingestionService;
    }

    /**
     * Searches Wikipedia for a given query, fetches the top article's content,
     * and ingests it into the vector store.
     *
     * @param query The search query.
     * @return A status message indicating success or failure of ingestion.
     */
    @Override
    public String searchAndIngest(String query) {
        try {
            // Step 1: Search Wikipedia for the query to get a relevant article title
            String searchResponse = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("action", "query")
                            .queryParam("list", "search")
                            .queryParam("srsearch", query)
                            .queryParam("format", "json")
                            .queryParam("srlimit", 1) // Get only the top result (can increase if needed)
                            .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block(); // Using block() for simplicity, consider reactive approach for production

            JsonNode searchJson = objectMapper.readTree(searchResponse);
            JsonNode searchResults = searchJson.path("query").path("search");

            if (searchResults.isArray() && searchResults.size() > 0) {
                String title = searchResults.get(0).path("title").asText();
                System.out.println("Wikipedia search found article: '" + title + "' for query '" + query + "'");

                // Step 2: Fetch the full content (extract) of the found article
                String pageContentResponse = webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .queryParam("action", "query")
                                .queryParam("prop", "extracts")
                                .queryParam("explaintext", true) // Return plain text
                                .queryParam("redirects", true) // Resolve redirects
                                // Optional: You can specify a character limit if you don't want the *entire* page,
                                // e.g., .queryParam("exchars", 5000) for 5000 characters.
                                // By default, without exintro or exchars/exsentences, it often returns a significant portion.
                                .queryParam("titles", title)
                                .queryParam("format", "json")
                                .build())
                        .retrieve()
                        .bodyToMono(String.class)
                        .block(); // Using block()

                JsonNode pageContentJson = objectMapper.readTree(pageContentResponse);
                JsonNode pagesNode = pageContentJson.path("query").path("pages");

                // Wikipedia API returns page content under a dynamic key (the page ID)
                Iterator<JsonNode> elements = pagesNode.elements();
                if (elements.hasNext()) {
                    JsonNode page = elements.next();
                    String extract = page.path("extract").asText();
                    if (extract != null && !extract.isEmpty()) {
                        ingestionService.ingestContent(extract, "Wikipedia: " + title); // Ingest the content
                        return "Successfully ingested Wikipedia information about: " + title;
                    } else {
                        return "Could not extract content from Wikipedia article: " + title + ". Extract was empty.";
                    }
                }
            }
            return "No relevant Wikipedia articles found for: '" + query + "'.";

        } catch (Exception e) {
            System.err.println("Error during Wikipedia ingestion for query '" + query + "': " + e.getMessage());
            return "Failed to ingest information from Wikipedia due to an error: " + e.getMessage();
        }
    }
}