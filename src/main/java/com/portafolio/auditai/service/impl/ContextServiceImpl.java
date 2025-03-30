package com.portafolio.auditai.service.impl;

import com.portafolio.auditai.dto.context.*;
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

    @Override
    public String getRules(AuditConfigDto auditConfigDto) {
        List<String> prompts = new ArrayList<>();

        ContextAuditDto context = auditConfigDto.getContextAuditDto();
        ConfigIADto configIA = auditConfigDto.getConfigIADto();

        // Construcción dinámica del prompt
        String prompt1 = "Genera una lista de reglas de auditoría basadas en el contexto proporcionado. " +
                "Cada regla debe incluir un nombre, descripción, normativa relacionada, severidad y campos involucrados.\n\n" +
                "Datos de entrada:\n" +
                "- Sector: " + context.getSector() + "\n" +
                "- Tipo de auditoría: " + context.getAuditType() + "\n" +
                "- Normativas aplicables: " + String.join(", ", context.getNormativas()) + "\n" +
                "- Campos disponibles: " + String.join(", ", context.getMetadata().getHeadCsv()) + "\n" +
                "- Nivel de detalle: " + configIA.getDetailLevel() + "\n" +
                "- Idioma: " + configIA.getLanguage() + "\n\n" +
                "Formato de salida esperado:\n" +
                "[\n" +
                "  {\n" +
                "    \"nombre\": \"Detección de transacciones sospechosas (AML)\",\n" +
                "    \"descripcion\": \"Alertar si una transacción supera los $10,000 sin aprobación de gerencia.\",\n" +
                "    \"normativa_relacionada\": \"AML\",\n" +
                "    \"severidad\": \"alta\",\n" +
                "    \"campos_involucrados\": [\"monto\", \"aprobacion_gerencia\"]\n" +
                "  },\n" +
                "  {\n" +
                "    \"nombre\": \"Segregación de funciones (SOX)\",\n" +
                "    \"descripcion\": \"Evitar que el mismo empleado registre y apruebe una transacción.\",\n" +
                "    \"normativa_relacionada\": \"SOX\",\n" +
                "    \"severidad\": \"media\",\n" +
                "    \"campos_involucrados\": [\"empleado\"]\n" +
                "  }\n" +
                "]";

        prompts.add(prompt1);
        return String.join("\n", prompts);
    }


}
