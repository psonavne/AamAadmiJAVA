package com.knowledge.chatbot.controller;

import com.knowledge.chatbot.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from our React frontend (default React dev server port)
public class ChatController {

    @Autowired
    ChatService chatService;

    @GetMapping("/getResponse")
    public ResponseEntity<String> generateResponse(@RequestParam String prompt){

        String response=  chatService.getResponse(prompt);
        System.out.println("Got Input as : " + prompt);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/ask")
    public String ask(@RequestParam(value = "prompt", defaultValue = "What is Spring AI?") String prompt) {
        return chatService.getChatResponse(prompt);
    }
}