package com.pri.ai.springai.service;

import org.springframework.ai.openai.llm.OpenAiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService{

    public static String LIMIT_CHARACTER = "and limit my response in 50 characters";
    @Autowired
    OpenAiClient aiClient;

    @Override
    public String getResponse(String prompt) {
        aiClient.setModel("gpt-4");
        aiClient.setTemperature(0D);
        String response = aiClient.generate(prompt + LIMIT_CHARACTER);
        System.out.println("AI Response: " + response);
        return response;
    }

}
