package com.pri.ai.springai.service;

import org.springframework.ai.openai.llm.OpenAiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CricketServiceImpl implements CricketService{

    public static final String GET_LATEST_IPL_MATCH = """
       Give me score of latest IPL match and what was the turning point""";


    @Autowired
    OpenAiClient aiClient;


    @Override
    public String getCricketUpdate() {
        return aiClient.generate(GET_LATEST_IPL_MATCH);
    }
}
