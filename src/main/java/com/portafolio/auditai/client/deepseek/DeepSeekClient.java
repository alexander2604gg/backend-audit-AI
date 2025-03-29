package com.portafolio.auditai.client.deepseek;

import com.portafolio.auditai.config.DeepSeekConfig;
import com.portafolio.auditai.dto.DeepSeekRequestDto;
import com.portafolio.auditai.dto.DeepSeekResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "deepseek-service", url = "${deepseek.api.base-url}", configuration = DeepSeekConfig.class)
public interface DeepSeekClient {

    @PostMapping(value = "/chat/completions", consumes = MediaType.APPLICATION_JSON_VALUE)
    DeepSeekResponseDto chatCompletion(@RequestBody DeepSeekRequestDto request);

}
