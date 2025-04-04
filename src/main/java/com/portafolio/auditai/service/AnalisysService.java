package com.portafolio.auditai.service;

import com.portafolio.auditai.dto.AuditRequestDto;
import com.portafolio.auditai.dto.AuditResponseDto;
import com.portafolio.auditai.dto.DashboardDto;

import java.util.List;

public interface AnalisysService {

    DashboardDto getAuditAnalysis(AuditRequestDto auditRequestDto);

}
