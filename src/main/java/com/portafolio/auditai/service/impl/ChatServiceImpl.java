package com.portafolio.auditai.service.impl;

import com.portafolio.auditai.client.deepseek.DeepSeekClient;
import com.portafolio.auditai.dto.DeepSeekRequestDto;
import com.portafolio.auditai.dto.DeepSeekResponseDto;
import com.portafolio.auditai.dto.Message;
import com.portafolio.auditai.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final DeepSeekClient deepSeekClient;

    public DeepSeekResponseDto getResponse(String userInput) {
        // 1. Construye el request correctamente
        Message messageFromBackend = new Message("system" , "Actúa como un auditor financiero especializado en la " +
                "detección de irregularidades. Analiza estados financieros, balances, estados de resultados y flujos de efectivo " +
                "para identificar inconsistencias, anomalías o posibles fraudes. Examina patrones sospechosos, discrepancias en los" +
                " datos y cualquier indicio de manipulación contable. Presenta tu análisis con una justificación clara y basada en " +
                "datos.");
        Message messageResponse = new Message("system", "\"Quiero que analices los estados financieros proporcionados y detectes posibles irregularidades. Tu respuesta debe incluir dos partes:  \n" +
                "\n" +
                "1. **Observación general:** Un análisis detallado sobre posibles inconsistencias, riesgos financieros o anomalías en los datos.  \n" +
                "2. **JSON para dashboard:** Un JSON estructurado con métricas clave, indicadores de riesgo y observaciones resumidas en un formato que pueda ser utilizado para visualizar en un dashboard.  ");
        Message messageFromUser = new Message("user" ,userInput);
        DeepSeekRequestDto request = new DeepSeekRequestDto(
                "deepseek-chat",
                List.of(messageFromBackend , messageFromUser),
                0.5
        );

        return deepSeekClient.chatCompletion(request);
    }

}
