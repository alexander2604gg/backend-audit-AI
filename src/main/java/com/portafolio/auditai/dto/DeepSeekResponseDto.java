package com.portafolio.auditai.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeepSeekResponseDto {
    private String id;
    private List<Choice> choices;

    @Data
    public static class Choice {
        private Message message;
        private int index;
    }
}
