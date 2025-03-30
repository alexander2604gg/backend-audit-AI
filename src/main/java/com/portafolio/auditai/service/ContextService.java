package com.portafolio.auditai.service;

import com.portafolio.auditai.dto.context.*;
import reactor.core.publisher.Mono;

public interface ContextService {
    RecommendedRulesDto getRulesRecommendations(AuditParametersDto auditParametersDto);
    Mono<String> getRulesRecommendationsReactive(AuditParametersDto auditParametersDto);
    RuleListDto getRules (AuditConfigDto auditConfigDto);
}
