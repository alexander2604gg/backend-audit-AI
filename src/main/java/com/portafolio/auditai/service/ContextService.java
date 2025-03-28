package com.portafolio.auditai.service;

import com.portafolio.auditai.dto.context.AuditParametersDto;
import com.portafolio.auditai.dto.context.RecommendedRules;

public interface ContextService {
    RecommendedRules getRulesRecommendations(AuditParametersDto auditParametersDto);
}
