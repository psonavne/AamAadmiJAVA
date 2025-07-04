package com.knowledge.chatbot.service;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentIngestionService {
    String ingestDocument(MultipartFile file);

    String ingestUrl(String url);
}
