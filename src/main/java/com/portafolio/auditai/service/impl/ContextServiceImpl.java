package com.portafolio.auditai.service.impl;

import com.portafolio.auditai.dto.context.AuditParametersDto;
import com.portafolio.auditai.dto.context.RecommendedRules;
import com.portafolio.auditai.service.ChatService;
import com.portafolio.auditai.service.ContextService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        prompts.add(prompt1);
        prompts.add(prompt2);
        prompts.add(prompt3);

        String response = chatService.getResponse("system", prompts);

        // Declaración fuera del bloque try
        RecommendedRules recommendedRules = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            recommendedRules = objectMapper.readValue(response, RecommendedRules.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al convertir la respuesta JSON: " + response);
        }

        return recommendedRules;
    }

}
