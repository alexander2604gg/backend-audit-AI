package com.portafolio.auditai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableReactiveFeignClients
public class AuditAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuditAiApplication.class, args);
	}

}
