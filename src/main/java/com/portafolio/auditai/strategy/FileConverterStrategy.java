package com.portafolio.auditai.strategy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

public interface FileConverterStrategy {
    String convertFileToJson (MultipartFile file);
    String getType();
}
