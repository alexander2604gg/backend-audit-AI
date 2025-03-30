package com.portafolio.auditai.service.impl;

import com.portafolio.auditai.dto.context.AuditParametersDto;
import com.portafolio.auditai.dto.context.RecommendedRulesDto;
import com.portafolio.auditai.enums.EnumValueRole;
import com.portafolio.auditai.service.ChatService;
import com.portafolio.auditai.service.ContextService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portafolio.auditai.service.JsonParserService;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class ContextServiceImpl implements ContextService {

    private final ChatService chatService;

    private final JsonParserService jsonParserService;

    @Override
    public RecommendedRulesDto getRulesRecommendations(AuditParametersDto auditParametersDto) {
        List<String> prompts = new ArrayList<>();
        String prompt1 = "Genera una lista de normativas recomendadas para una auditoría basado en la siguiente información: " +
                "Sector: " + auditParametersDto.getSector() +
                ", Tipo de auditoría: " + auditParametersDto.getAuditType();
        String prompt2 = "Tu respuesta solo será en formato JSON";
        String prompt3 = "La estructura del JSON será así: {\n" +
                "  \"recommendedRules\": [\"SOX\", \"AML\", \"Basel III\"]\n" +
                "}";
        String prompt4 = "Asegurate que sea solo un JSON limpio, ya que yo lo convertiré a un json real con la libreria Jackson de java";
        prompts.add(prompt1);
        prompts.add(prompt2);
        prompts.add(prompt3);
        prompts.add(prompt4);

        String response = chatService.getResponse(EnumValueRole.SYSTEM.getRole(), prompts);
        String json = jsonParserService.extractJson(response);
        return jsonParserService.toObject(json , RecommendedRulesDto.class);
    }

    @Override
    public Mono<String> getRulesRecommendationsReactive(AuditParametersDto auditParametersDto) {
        return null;
    }


}
