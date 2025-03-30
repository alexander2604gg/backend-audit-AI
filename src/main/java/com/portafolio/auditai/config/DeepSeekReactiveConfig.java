package com.portafolio.auditai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import reactivefeign.ReactiveOptions;
import reactivefeign.client.ReactiveHttpRequestInterceptor;
import reactivefeign.webclient.WebReactiveOptions;
import reactor.core.publisher.Mono;

import java.util.Collections;

public class DeepSeekReactiveConfig {

    @Value("${deepseek.api.key}")
    private String apiKey;

    @Bean
    public ReactiveHttpRequestInterceptor authInterceptor() {
        return request -> {
            request.headers().put("Authorization", Collections.singletonList("Bearer " + apiKey));
            return Mono.just(request);
        };
    }

    @Bean
    public ReactiveOptions reactiveOptions() {
        return new WebReactiveOptions.Builder()
                .setConnectTimeoutMillis(5000)
                .setReadTimeoutMillis(5000)
                .build();
    }
}
