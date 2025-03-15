package com.portafolio.auditai.service;

import com.portafolio.auditai.dto.DeepSeekResponseDto;

public interface ChatService {

    DeepSeekResponseDto getResponse(String prompt);
}
