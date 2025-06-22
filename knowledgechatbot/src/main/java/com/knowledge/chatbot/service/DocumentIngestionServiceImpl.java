package com.knowledge.chatbot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.DocumentReader;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.reader.jsoup.JsoupDocumentReader;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class DocumentIngestionServiceImpl implements DocumentIngestionService {

    private static final Logger logger = LoggerFactory.getLogger(DocumentIngestionService.class);

    @Autowired
    private VectorStore vectorStore;

   // @Autowired
    private final TokenTextSplitter tokenTextSplitter;

    @Autowired
    private EmbeddingModel embeddingModel;


    public DocumentIngestionServiceImpl(VectorStore vectorStore) {
        //this.vectorStore = vectorStore;
        this.tokenTextSplitter = TokenTextSplitter.builder()
                .withChunkSize(1000) // Example: 1000 tokens per chunk
                // You can add .withChunkOverlap() here if you need a specific overlap,
                // but the builder might not expose it directly in this version or has a default.
                // If it does, it's .withChunkOverlap(200)
                .build();
    }

    @Value("classpath:/cricket_knowledge/cricket_rulebook.pdf")
    private Resource cricketRulesPdfResource;
    /**
     * Ingests a MultipartFile into the vector store.
     * Automatically detects file type based on content or filename.
     * @param file The MultipartFile to ingest.
     * @return A message indicating success or failure.
     */
    /**
     * Ingests a MultipartFile into the vector store.
     * Automatically detects file type based on content or filename.
     * @param file The MultipartFile to ingest.
     * @return A message indicating success or failure.
     */
    @Override
    public String ingestDocument(MultipartFile file) {
        if (file.isEmpty()) {
            return "File is empty.";
        }

        try {
            Resource resource = new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            };

            DocumentReader reader = getDocumentReader(resource, file.getContentType(), file.getOriginalFilename());

            if (reader == null) {
                return "Unsupported file type or content. Cannot ingest: " + file.getOriginalFilename();
            }

            // Read documents, each document might be a page, paragraph, or whole file depending on reader
            List<Document> documents = reader.get();

            if (documents.isEmpty()) {
                logger.warn("No documents extracted from file: {}", file.getOriginalFilename());
                return "No content extracted from file: " + file.getOriginalFilename();
            }

            // Apply text splitting here
            List<Document> chunks = tokenTextSplitter.split(documents); // NEW: Split documents into chunks

            // Add metadata for source tracking to each chunk
            chunks.forEach(doc -> { // Iterate over chunks, not original documents
                doc.getMetadata().put("source_filename", file.getOriginalFilename());
                // No need to set ID explicitly; Spring AI will assign one or it's already there
            });

            vectorStore.add(chunks); // NEW: Add the chunks to the vector store
            logger.info("Successfully ingested {} chunks from file: {}", chunks.size(), file.getOriginalFilename());
            return "Successfully ingested " + chunks.size() + " document chunks from " + file.getOriginalFilename();

        } catch (IOException e) {
            logger.error("Error ingesting document {}: {}", file.getOriginalFilename(), e.getMessage(), e);
            return "Failed to ingest document: " + e.getMessage();
        }
    }

    /**
     * Ingests content from a given URL into the vector store.
     * @param url The URL string to ingest.
     * @return A message indicating success or failure.
     */
    @Override
    public String ingestUrl(String url) {
        try {
            Resource resource = new org.springframework.core.io.UrlResource(url);
            // JsoupDocumentReader is ideal for web pages
            JsoupDocumentReader reader = new JsoupDocumentReader(resource);

            List<Document> documents = reader.get();

            if (documents.isEmpty()) {
                logger.warn("No documents extracted from URL: {}", url);
                return "No content extracted from URL: " + url;
            }

            // Apply text splitting here for URLs too
            List<Document> chunks = tokenTextSplitter.split(documents); // NEW: Split URL documents into chunks

            chunks.forEach(doc -> { // Iterate over chunks
                doc.getMetadata().put("source_url", url);
                // No need to set ID explicitly
            });

            vectorStore.add(chunks); // NEW: Add the chunks to the vector store
            logger.info("Successfully ingested {} chunks from URL: {}", chunks.size(), url);
            return "Successfully ingested " + chunks.size() + " document chunks from " + url;

        } catch (MalformedURLException e) {
            logger.error("Invalid URL format {}: {}", url, e.getMessage(), e);
            return "Failed to ingest URL: Invalid URL format.";
        } catch (IOException e) {
            logger.error("Error ingesting URL {}: {}", url, e.getMessage(), e);
            return "Failed to ingest URL: " + e.getMessage();
        }
    }

    private DocumentReader getDocumentReader(Resource resource, String contentType, String filename) {
        // Prioritize content type if reliable, otherwise fallback to filename extension
        if (contentType != null) {
            if (contentType.contains("pdf")) {
                logger.info("Using PagePdfDocumentReader for PDF.");
                return new PagePdfDocumentReader(resource);
            } else if (contentType.contains("excel") || contentType.contains("spreadsheet") ||
                    contentType.contains("word") || contentType.contains("powerpoint") ||
                    contentType.contains("text/plain")) {
                logger.info("Using TikaDocumentReader for Office/Text file (contentType: {}).", contentType);
                return new TikaDocumentReader(resource);
            } else if (contentType.contains("html")) {
                logger.info("Using JsoupDocumentReader for HTML file (contentType: {}).", contentType);
                return new JsoupDocumentReader(resource);
            }
        }

        // Fallback to filename extension if content type is not specific enough or missing
        if (filename != null) {
            String lowerCaseFilename = filename.toLowerCase();
            if (lowerCaseFilename.endsWith(".pdf")) {
                logger.info("Using PagePdfDocumentReader for PDF (filename: {}).", filename);
                return new PagePdfDocumentReader(resource);
            } else if (lowerCaseFilename.endsWith(".docx") || lowerCaseFilename.endsWith(".doc") ||
                    lowerCaseFilename.endsWith(".pptx") || lowerCaseFilename.endsWith(".ppt") ||
                    lowerCaseFilename.endsWith(".xlsx") || lowerCaseFilename.endsWith(".xls") ||
                    lowerCaseFilename.endsWith(".txt")) {
                logger.info("Using TikaDocumentReader for Office/Text file (filename: {}).", filename);
                return new TikaDocumentReader(resource);
            } else if (lowerCaseFilename.endsWith(".html") || lowerCaseFilename.endsWith(".htm")) {
                logger.info("Using JsoupDocumentReader for HTML file (filename: {}).", filename);
                return new JsoupDocumentReader(resource);
            }
        }

        logger.warn("No suitable DocumentReader found for content type: {} and filename: {}", contentType, filename);
        return null;
    }

    /**
     * Ingests documents from the predefined cricket rules PDF file.
     * This method can be called on application startup or via a manual trigger.
     */
    @Override
    public void ingestPredefinedDocuments() {
        // Corrected: Using SearchRequest.builder() for retrieve check
        if (vectorStore.similaritySearch(SearchRequest.builder().query("cricket rules").topK(3).build()).isEmpty()) {
            System.out.println("Ingesting predefined cricket rules PDF documents...");
            try {
                PagePdfDocumentReader reader = new PagePdfDocumentReader(cricketRulesPdfResource);

               /* List<Document> documents = pdfReader.get();

                documents.forEach(doc -> {
                    doc.getMetadata().put("source", "Predefined Cricket Rules PDF");
                });

                List<Document> chunks = tokenTextSplitter.split(documents);
                vectorStore.add(chunks);*/

                // Read documents, each document might be a page, paragraph, or whole file depending on reader
                List<Document> documents = reader.get();

                if (documents.isEmpty()) {
                    logger.warn("No documents extracted from file: {}", cricketRulesPdfResource.getFilename());
                }

                // Apply text splitting here
                List<Document> chunks = tokenTextSplitter.split(documents); // NEW: Split documents into chunks

                // Add metadata for source tracking to each chunk
                chunks.forEach(doc -> { // Iterate over chunks, not original documents
                    doc.getMetadata().put("source_filename", cricketRulesPdfResource.getFilename());
                    // No need to set ID explicitly; Spring AI will assign one or it's already there
                });

                vectorStore.add(chunks); // NEW: Add the chunks to the vector store
                logger.info("Successfully ingested {} chunks from file: {}", chunks.size(), cricketRulesPdfResource.getFilename());
            } catch (Exception e) {
                System.err.println("Failed to read predefined cricket rules PDF: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Predefined cricket rules PDF data already exists in vector store. Skipping ingestion.");
        }
    }

    /**
     * Ingests a single piece of text content into the vector store.
     * This is useful for dynamic ingestion from sources like Wikipedia.
     *
     * @param content The text content to ingest.
     * @param source  A string identifying the source of the content (e.g., "Wikipedia: Article Title").
     */
    @Override
    public void ingestContent(String content, String source) {
        Document document = new Document(content, Map.of("source", source));
        List<Document> chunks = tokenTextSplitter.split(List.of(document));

        // Corrected: Using SearchRequest.builder() for retrieve check
        List<Document> existingDocs = vectorStore.similaritySearch(SearchRequest.builder().query(content).topK(3).build());
        boolean alreadyExists = existingDocs.stream()
                .anyMatch(doc -> Objects.equals(doc.getFormattedContent(), content));

        if (!alreadyExists) {
            vectorStore.add(chunks);
            System.out.println("Dynamically ingested content from: " + source + ". Chunks: " + chunks.size());
        } else {
            System.out.println("Content from " + source + " (similar to already existing) skipping dynamic ingestion.");
        }
    }
}
