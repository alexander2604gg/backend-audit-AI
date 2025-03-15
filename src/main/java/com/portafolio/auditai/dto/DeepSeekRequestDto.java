package com.portafolio.auditai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class DeepSeekRequestDto {
    private String model;
    private List<Message> messages;
    private double temperature;

    public DeepSeekRequestDto(String model, List<Message> messages, double temperature) {
        this.model = model;
        this.messages = messages;
        this.temperature = temperature;
    }
}
