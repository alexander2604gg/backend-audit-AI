package com.portafolio.auditai.dto.context;

import lombok.Data;

import java.util.List;

@Data
public class ContextAuditDto {
    private String sector;
    private String auditType;
    private List<String> normativas;
    private MetaDataDto metadata;
}
