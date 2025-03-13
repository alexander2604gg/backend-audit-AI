package com.portafolio.auditai.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileConversionService {
    String convertFileToJson(MultipartFile file, String type);
}
