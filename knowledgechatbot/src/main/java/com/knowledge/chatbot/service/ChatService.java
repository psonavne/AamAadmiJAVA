package com.knowledge.chatbot.service;

public interface ChatService {

    String getResponse(String prompt);

    String getChatResponse(String prompt);

    String chat(String message);
}
