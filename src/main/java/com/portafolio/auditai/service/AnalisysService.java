package com.portafolio.auditai.service;

import com.portafolio.auditai.dto.AuditRequestDto;
import com.portafolio.auditai.dto.AuditResponseDto;

public interface AnalisysService {

    AuditResponseDto getAuditAnalysis(AuditRequestDto auditRequestDto);

}
