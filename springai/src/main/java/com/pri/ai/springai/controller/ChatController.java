package com.pri.ai.springai.controller;

import com.pri.ai.springai.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ai")
public class ChatController {

    @Autowired
    ChatService chatService;

    @GetMapping("/getResponse")
    public ResponseEntity<String> generateResponse(@RequestParam String prompt){

        String response=  chatService.getResponse(prompt);
        System.out.println("Got Input as : " + prompt);
        return ResponseEntity.ok(response);
    }
}