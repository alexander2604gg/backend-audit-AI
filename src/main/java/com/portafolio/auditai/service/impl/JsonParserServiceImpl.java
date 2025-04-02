package com.portafolio.auditai.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portafolio.auditai.service.JsonParserService;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class JsonParserServiceImpl implements JsonParserService {

    private final ObjectMapper objectMapper;

    @Override
    public <T> T toObject(String json, Class<T> object) {
        try {
            return objectMapper.readValue(json , object);
        } catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> toList(String json, Class<T> objectType) {
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, objectType));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String extractJson(String rawResponse) {

        Pattern pattern = Pattern.compile("\\{.*?\\}", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(rawResponse);

        if (matcher.find()) {
            String potentialJson = matcher.group(0);

            // Validar si es un JSON válido
            try {
                new JSONObject(potentialJson);
                return potentialJson.replaceAll("\\s+", " ");
            } catch (JSONException e) {
                // Si falla, buscar posibles JSONs completos manualmente
                int start = rawResponse.indexOf('{');
                int end = rawResponse.lastIndexOf('}');
                if (start != -1 && end != -1 && end > start) {
                    String manualJson = rawResponse.substring(start, end + 1);
                    new JSONObject(manualJson); // Validar nuevamente
                    return manualJson.replaceAll("\\s+", " ");
                }
            }
        }
        throw new IllegalArgumentException("No se encontró JSON válido en la respuesta");
    }
}
