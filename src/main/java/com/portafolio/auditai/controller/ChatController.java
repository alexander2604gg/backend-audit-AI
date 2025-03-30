package com.portafolio.auditai.controller;

import com.portafolio.auditai.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;
    /*@PostMapping("/completions")
    public ResponseEntity<String> handleChatRequest(@RequestBody String message) {
        String response = chatService.getResponse(message);
        return ResponseEntity.ok(response);
    }*/

    @PostMapping("/completions/{role}")
    public Mono<ResponseEntity<String>> handleChatRequest(@PathVariable String role, @RequestBody List<String> inputs) {
        return Flux.fromIterable(inputs)
                .collectList()
                .flatMap(prompts -> chatService.getResponseReactive(role, Flux.fromIterable(prompts)))
                .map(ResponseEntity::ok);
    }


}
