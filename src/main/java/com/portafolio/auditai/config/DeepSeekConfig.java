package com.portafolio.auditai.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class DeepSeekConfig {
    @Bean
    public RequestInterceptor authInterceptor(
            @Value("${deepseek.api.key}") String apiKey
    ) {
        return template -> template.header(
                "Authorization",
                "Bearer " + apiKey
        );
    }
}