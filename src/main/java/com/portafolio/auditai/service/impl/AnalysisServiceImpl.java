package com.portafolio.auditai.service.impl;

import com.portafolio.auditai.dto.AuditRequestDto;
import com.portafolio.auditai.dto.AuditResponseDto;
import com.portafolio.auditai.dto.DashboardDto;
import com.portafolio.auditai.enums.EnumValueRole;
import com.portafolio.auditai.service.AnalisysService;
import com.portafolio.auditai.service.ChatService;
import com.portafolio.auditai.service.JsonParserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AnalysisServiceImpl implements AnalisysService {

    public final ChatService chatService;
    private final JsonParserService jsonParserService;

    @Override
    public DashboardDto getAuditAnalysis(AuditRequestDto auditRequestDto) {
        List<String> prompts = new ArrayList<>();

        // Tipos de gráficos permitidos
        List<String> validChartTypes = List.of("Line", "Bar", "Pie", "Radar", "Doughnut", "PolarArea", "Bubble", "Scatter");

        // Construcción dinámica del prompt
        String prompt1 = "Genera un análisis de auditoría en formato JSON considerando el siguiente contexto: \n" +
                "- Tipo de Auditoría: " + auditRequestDto.getAuditType() + "\n" +
                "- Sector: " + auditRequestDto.getSector() + "\n" +
                "- Normativas Aplicables: " + String.join(", ", auditRequestDto.getRegulations()) + "\n" +
                "- Reglas Aplicadas: " + String.join(", ", auditRequestDto.getRules()) + "\n" +
                "- Datos CSV: " + auditRequestDto.getCsvData().toString() + "\n\n" +
                "Este es el JSON exacto del formato que me tienes que retornar, recuerda seguir exactamente esa estructura es:\n" +
                "{\n" +
                "  \"auditResponseDtoList\": [\n" +
                "    {\n" +
                "      \"title\": \"Análisis de Auditoría\",\n" +
                "      \"typeGrafic\": \"[DETERMINADO POR LA IA - ESCOGE ENTRE: Line, Bar, Pie, Radar, Doughnut, PolarArea, Bubble, Scatter]\",\n" +
                "      \"chartData\": {\n" +
                "        \"labels\": [\"Ene\", \"Feb\", \"Mar\", \"Abr\", \"May\", \"Jun\"],\n" +
                "        \"datasets\": [\n" +
                "          {\n" +
                "            \"label\": \"Cumplimiento\",\n" +
                "            \"data\": [75, 80, 70, 90, 85, 95],\n" +
                "            \"backgroundColor\": [\"rgba(46, 204, 113, 0.7)\", \"rgba(46, 204, 113, 0.7)\", \"rgba(46, 204, 113, 0.7)\", \"rgba(46, 204, 113, 0.7)\", \"rgba(46, 204, 113, 0.7)\", \"rgba(46, 204, 113, 0.7)\"],\n" +
                "            \"borderColor\": [\"rgba(46, 204, 113, 1)\", \"rgba(46, 204, 113, 1)\", \"rgba(46, 204, 113, 1)\", \"rgba(46, 204, 113, 1)\", \"rgba(46, 204, 113, 1)\", \"rgba(46, 204, 113, 1)\"],\n" +
                "            \"borderWidth\": 1\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"description\": \"Resumen del análisis de auditoría basado en las normativas y reglas proporcionadas.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Análisis de Auditoría 2\",\n" +
                "      \"typeGrafic\": \"Bar\",\n" +
                "      \"chartData\": {\n" +
                "        \"labels\": [\"Ene\", \"Feb\", \"Mar\", \"Abr\"],\n" +
                "        \"datasets\": [\n" +
                "          {\n" +
                "            \"label\": \"Cumplimiento\",\n" +
                "            \"data\": [60, 70, 50, 80],\n" +
                "            \"backgroundColor\": [\"rgba(52, 152, 219, 0.7)\", \"rgba(52, 152, 219, 0.7)\", \"rgba(52, 152, 219, 0.7)\", \"rgba(52, 152, 219, 0.7)\"],\n" +
                "            \"borderColor\": [\"rgba(52, 152, 219, 1)\", \"rgba(52, 152, 219, 1)\", \"rgba(52, 152, 219, 1)\", \"rgba(52, 152, 219, 1)\"],\n" +
                "            \"borderWidth\": 1\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"description\": \"Análisis adicional de las reglas aplicadas.\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";




        prompts.add(prompt1);

        // Llamada al servicio de IA
        String response = chatService.getResponse(EnumValueRole.SYSTEM.getRole(), prompts);
        String json = jsonParserService.extractJson(response);

        // Parsear el JSON recibido
        DashboardDto dashboardDto = jsonParserService.toObject(json , DashboardDto.class);

        return dashboardDto;
    }



}
