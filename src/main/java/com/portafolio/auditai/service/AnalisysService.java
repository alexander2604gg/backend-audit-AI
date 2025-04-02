package com.portafolio.auditai.service;

import com.portafolio.auditai.dto.AuditRequestDto;
import com.portafolio.auditai.dto.AuditResponseDto;

import java.util.List;

public interface AnalisysService {

    List<AuditResponseDto> getAuditAnalysis(AuditRequestDto auditRequestDto);

}
