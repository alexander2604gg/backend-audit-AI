package com.portafolio.auditai.service;

import java.util.List;

public interface ChatService {
    public String getResponse(String role , List<String> input);
}
