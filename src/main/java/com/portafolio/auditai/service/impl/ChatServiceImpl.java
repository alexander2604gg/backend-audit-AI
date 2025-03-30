package com.portafolio.auditai.service.impl;

import com.portafolio.auditai.client.deepseek.DeepSeekClient;
import com.portafolio.auditai.dto.DeepSeekRequestDto;
import com.portafolio.auditai.dto.DeepSeekResponseDto;
import com.portafolio.auditai.dto.Message;
import com.portafolio.auditai.enums.EnumValueModel;
import com.portafolio.auditai.reactiveclient.DeepSeekReactiveClient;
import com.portafolio.auditai.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final DeepSeekClient deepSeekClient;
    private final DeepSeekReactiveClient deepSeekReactiveClient;

    public String getResponse(String role , List<String> input) {
        List<Message> messages = new ArrayList<>();
        input.forEach((i) -> {
            Message message = new Message(role , i);
            messages.add(message);
        });
        DeepSeekRequestDto request = new DeepSeekRequestDto(EnumValueModel.DEEPSEEK_MODEL.getModel(), messages, 0.7);
        DeepSeekResponseDto response = deepSeekClient.chatCompletion(request);
        return response.getChoices().get(0)
                .getMessage().getContent();
    }
    @Override
    public Mono<String> getResponseReactive(String role, Flux<String> input) {
        return input
                .map(i -> new Message(role, i))
                .collectList() // Recopila los mensajes en una lista
                .flatMap(messages -> {
                    DeepSeekRequestDto requestDto = new DeepSeekRequestDto(EnumValueModel.DEEPSEEK_MODEL.getModel(), messages, 0.7);
                    return deepSeekReactiveClient.chatCompletion(requestDto);
                })
                .map(responseDto -> responseDto.getChoices().get(0).getMessage().getContent());
    }
}
