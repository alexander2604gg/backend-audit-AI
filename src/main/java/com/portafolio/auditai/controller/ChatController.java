package com.portafolio.auditai.controller;

import com.portafolio.auditai.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/completions")
    public ResponseEntity<String> handleChatRequest(@RequestBody String message) {

        String response = chatService.getResponse(message);
        return ResponseEntity.ok(response);


    }

}
