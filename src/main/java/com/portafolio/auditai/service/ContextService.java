package com.portafolio.auditai.service;

import com.portafolio.auditai.dto.context.AuditConfigDto;
import com.portafolio.auditai.dto.context.AuditParametersDto;
import com.portafolio.auditai.dto.context.RecommendedRulesDto;
import com.portafolio.auditai.dto.context.RuleDto;
import reactor.core.publisher.Mono;

public interface ContextService {
    RecommendedRulesDto getRulesRecommendations(AuditParametersDto auditParametersDto);
    Mono<String> getRulesRecommendationsReactive(AuditParametersDto auditParametersDto);
    RuleDto getRules (AuditConfigDto auditConfigDto);
}
