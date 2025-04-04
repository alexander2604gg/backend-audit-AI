package com.portafolio.auditai.dto;

import lombok.Data;

import java.util.List;

@Data
public class DashboardDto {
    List<AuditResponseDto> auditResponseDtoList;
}
