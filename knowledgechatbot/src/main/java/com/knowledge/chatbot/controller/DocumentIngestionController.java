package com.knowledge.chatbot.controller;


import com.knowledge.chatbot.service.DocumentIngestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/ingest")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from React frontend
public class DocumentIngestionController {

    @Autowired
    private DocumentIngestionService documentIngestionService;

    /**
     * Endpoint to upload and ingest a document file.
     * @param file The document file to upload.
     * @return A message indicating the result of the ingestion.
     */
    @PostMapping("/document")
    public ResponseEntity<String> uploadDocument(@RequestParam("file") MultipartFile file) {
        String result = documentIngestionService.ingestDocument(file);
        return ResponseEntity.ok(result);
    }

    /**
     * Endpoint to ingest content from a given URL.
     * @param url The URL to ingest.
     * @return A message indicating the result of the ingestion.
     */
    @PostMapping("/url")
    public ResponseEntity<String> ingestUrl(@RequestParam("url") String url) {
        String result = documentIngestionService.ingestUrl(url);
        return ResponseEntity.ok(result);
    }
}