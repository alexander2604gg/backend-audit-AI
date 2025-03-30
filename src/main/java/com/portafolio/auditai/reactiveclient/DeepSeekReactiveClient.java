package com.portafolio.auditai.reactiveclient;

import com.portafolio.auditai.config.DeepSeekReactiveConfig;
import com.portafolio.auditai.dto.DeepSeekRequestDto;
import com.portafolio.auditai.dto.DeepSeekResponseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "deepseek-reactive-service", url = "${deepseek.api.base-url}" , configuration = DeepSeekReactiveConfig.class)
public interface DeepSeekReactiveClient {
    @PostMapping(value = "/chat/completions" , consumes = MediaType.APPLICATION_JSON_VALUE)
    Mono<DeepSeekResponseDto> chatCompletion(@RequestBody DeepSeekRequestDto request);
}
