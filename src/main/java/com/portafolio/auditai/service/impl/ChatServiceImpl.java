package com.portafolio.auditai.service.impl;

import com.portafolio.auditai.client.deepseek.DeepSeekClient;
import com.portafolio.auditai.dto.DeepSeekRequestDto;
import com.portafolio.auditai.dto.DeepSeekResponseDto;
import com.portafolio.auditai.dto.Message;
import com.portafolio.auditai.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final DeepSeekClient deepSeekClient;

    public String getResponse(String userInput) {
        // 1. Construye el request correctamente
        Message message = new Message("user" , userInput);
        DeepSeekRequestDto request = new DeepSeekRequestDto(
                "deepseek-chat",
                List.of(message),
                0.7
        );

        // 2. Obtiene la respuesta completa
        DeepSeekResponseDto response = deepSeekClient.chatCompletion(request);

        // 3. Extrae el contenido
        return response.getChoices().get(0)
                .getMessage().getContent();
    }

}
