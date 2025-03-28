package com.portafolio.auditai.service.impl;

import com.portafolio.auditai.dto.context.AuditParametersDto;
import com.portafolio.auditai.dto.context.RecommendedRules;
import com.portafolio.auditai.service.ChatService;
import com.portafolio.auditai.service.ContextService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class ContextServiceImpl implements ContextService {

    private final ChatService chatService;

    @Override
    public RecommendedRules getRulesRecommendations(AuditParametersDto auditParametersDto) {

        List<String> prompts = new ArrayList<>();
        String prompt1 = "Genera una lista de normativas recomendadas para una auditoría basado en la siguiente información: " +
                "Sector: " + auditParametersDto.getSector() +
                ", Tipo de auditoría: " + auditParametersDto.getAuditType();
        String prompt2 = "Tu respuesta solo será en formato JSON";
        String prompt3 = "La estructura del JSON será así: {\n" +
                "  \"normativas_recomendadas\": [\"SOX\", \"AML\", \"Basel III\"]\n" +
                "}";
        String prompt4 = "Asegurate que sea un JSON limpio, ya que yo lo converiré a un json real con la libreria Jackson de java, no envies los ``` que siempre mandas";
        prompts.add(prompt1);
        prompts.add(prompt2);
        prompts.add(prompt3);
        prompts.add(prompt4);

        String response = chatService.getResponse("system", prompts);
        String json = extractJson(response);
        // Declaración fuera del bloque try
        RecommendedRules recommendedRules = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            recommendedRules = objectMapper.readValue(json, RecommendedRules.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al convertir la respuesta JSON: " + response);
        }

        return recommendedRules;
    }

    public static String extractJson(String rawResponse) {
        // Expresión regular para encontrar contenido entre { y }, incluyendo saltos de línea
        Pattern pattern = Pattern.compile("\\{.*?\\}", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(rawResponse);

        if (matcher.find()) {
            String potentialJson = matcher.group(0);

            // Validar si es un JSON válido
            try {
                new JSONObject(potentialJson);
                return potentialJson.replaceAll("\\s+", " "); // Formatear espacios
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
