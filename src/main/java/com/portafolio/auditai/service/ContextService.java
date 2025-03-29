package com.portafolio.auditai.service;

import com.portafolio.auditai.dto.context.AuditParametersDto;
import com.portafolio.auditai.dto.context.RecommendedRulesDto;

public interface ContextService {
    RecommendedRulesDto getRulesRecommendations(AuditParametersDto auditParametersDto);
}
