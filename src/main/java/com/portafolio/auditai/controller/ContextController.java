package com.portafolio.auditai.controller;
import com.portafolio.auditai.dto.context.AuditParametersDto;
import com.portafolio.auditai.dto.context.RecommendedRules;
import com.portafolio.auditai.service.ContextService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ContextController {

    private final ContextService contextService;

    @PostMapping("/recommendations")
    public ResponseEntity<RecommendedRules> getRecommendations(@RequestBody AuditParametersDto auditParametersDto) {
        RecommendedRules recommendedRules = contextService.getRulesRecommendations(auditParametersDto);
        if (recommendedRules != null) {
            return new ResponseEntity<>(recommendedRules, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
