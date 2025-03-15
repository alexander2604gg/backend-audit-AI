package com.portafolio.auditai.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {
    private String role;
    private String content;

    public Message(String sender, String content) {
        this.role = sender;
        this.content = content;
    }

}