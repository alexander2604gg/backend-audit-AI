package com.portafolio.auditai.service.impl;

import com.portafolio.auditai.service.FileConversionService;
import com.portafolio.auditai.strategy.FileConverterStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FileConversionServiceImpl implements FileConversionService {

    private final Map<String, FileConverterStrategy> converterStrategies;

    @Autowired
    public FileConversionServiceImpl(List<FileConverterStrategy> strategies) {
        this.converterStrategies = strategies.stream()
                .collect(Collectors.toMap(FileConverterStrategy::getType, strategy -> strategy));
    }

    public String convertFileToJson(MultipartFile file, String type) {
        FileConverterStrategy converter = converterStrategies.get(type.toLowerCase());
        if (converter == null) {
            throw new IllegalArgumentException("No se encontró una estrategia para el tipo: " + type);
        }
        return converter.convertFileToJson(file);
    }

}
