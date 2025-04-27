package com.pri.ai.springai.controller;

import com.pri.ai.springai.service.CricketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ai")
public class CricketController {

    @Autowired
    CricketService cricketService;

    @GetMapping("/getIPLUpdate")
    public ResponseEntity<String> getIPLUpdate(){

        String response=  cricketService.getCricketUpdate();
        return ResponseEntity.ok(response);
    }
}