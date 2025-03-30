package com.portafolio.auditai.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ChatService {
    public String getResponse(String role , List<String> input);
    public Mono<String> getResponseReactive(String role , Flux<String> input);
}
