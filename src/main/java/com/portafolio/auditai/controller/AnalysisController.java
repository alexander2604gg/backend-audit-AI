package com.portafolio.auditai.controller;

import com.portafolio.auditai.dto.AuditRequestDto;
import com.portafolio.auditai.dto.AuditResponseDto;
import com.portafolio.auditai.dto.context.AuditParametersDto;
import com.portafolio.auditai.dto.context.RecommendedRulesDto;
import com.portafolio.auditai.service.AnalisysService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AnalysisController {

    private final AnalisysService analisysService;

    @PostMapping("/analysis")
    public ResponseEntity<AuditResponseDto> getAnalysis(@RequestBody AuditRequestDto auditParametersDto) {
        AuditResponseDto auditAnalysis = analisysService.getAuditAnalysis(auditParametersDto);
        if (auditAnalysis != null) {
            return new ResponseEntity<>(auditAnalysis, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
