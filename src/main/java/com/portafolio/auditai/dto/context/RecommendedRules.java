package com.portafolio.auditai.dto.context;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RecommendedRules {
    @JsonProperty("normativas_recomendadas")
    private List<String> recommendedRules;
}
