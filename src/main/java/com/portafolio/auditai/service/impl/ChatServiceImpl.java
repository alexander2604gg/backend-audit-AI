package com.portafolio.auditai.service.impl;

import com.portafolio.auditai.client.deepseek.DeepSeekClient;
import com.portafolio.auditai.dto.DeepSeekRequestDto;
import com.portafolio.auditai.dto.DeepSeekResponseDto;
import com.portafolio.auditai.dto.Message;
import com.portafolio.auditai.enums.EnumValueModel;
import com.portafolio.auditai.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final DeepSeekClient deepSeekClient;

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
}
