package com.pri.ai.springai.service;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import org.springframework.ai.openai.llm.OpenAiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBServiceImpl implements DBService{

    @Autowired
    OpenAiClient aiClient;

    @Override
    public String getQueryResult(String prompt){
        String result = null;

        ChatLanguageModel chatLanguageModel = OpenAiChatModel.builder().apiKey("key").modelName("gpt-4").maxTokens(50).build();

       /* EmbeddingStore embeddingStore = OracleEmbeddingStore.builder()
                .dataSource(myDataSource)
                .embeddingTable("my_embedding_table")
                .build();
*/


        return result;
    }
}
