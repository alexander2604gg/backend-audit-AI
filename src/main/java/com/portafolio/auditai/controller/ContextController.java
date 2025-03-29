package com.portafolio.auditai.controller;
import com.portafolio.auditai.dto.context.AuditParametersDto;
import com.portafolio.auditai.dto.context.RecommendedRulesDto;
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
    public ResponseEntity<RecommendedRulesDto> getRecommendations(@RequestBody AuditParametersDto auditParametersDto) {
        RecommendedRulesDto recommendedRulesDto = contextService.getRulesRecommendations(auditParametersDto);
        if (recommendedRulesDto != null) {
            return new ResponseEntity<>(recommendedRulesDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
