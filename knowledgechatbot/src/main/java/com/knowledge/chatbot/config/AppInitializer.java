package com.knowledge.chatbot.config;

import com.knowledge.chatbot.service.DocumentIngestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppInitializer {

    @Autowired
    private DocumentIngestionService documentIngestionService;

    @Bean // This method creates a CommandLineRunner bean
    public CommandLineRunner runOnStartup() {
        return args -> {
            System.out.println("Executing startup tasks: Ingesting predefined documents...");
            documentIngestionService.ingestPredefinedDocuments();
            System.out.println("Startup document ingestion completed.");
        };
    }
}