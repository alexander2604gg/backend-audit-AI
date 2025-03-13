package com.portafolio.auditai.strategy.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portafolio.auditai.strategy.FileConverterStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CsvToJsonConverter implements FileConverterStrategy {

    public String convertFileToJson(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            String headerLine = reader.readLine();
            if (headerLine == null) {
                throw new IOException("El archivo está vacío");
            }
            String[] headers = headerLine.split(",");

            List<Map<String, String>> records = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                Map<String, String> record = new HashMap<>();
                for (int i = 0; i < headers.length && i < values.length; i++) {
                    record.put(headers[i].trim(), values[i].trim());
                }
                records.add(record);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(records);
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"error\": \"" + e.getMessage() + "\"}";
        }
    }

    @Override
    public String getType() {
        return "csv";
    }

}
