package com.portafolio.auditai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AuditAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuditAiApplication.class, args);
	}

}
