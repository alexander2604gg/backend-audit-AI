package com.portafolio.auditai.dto;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class AuditRequestDto {
    private String auditType;
    private String sector;
    private List<String> regulations;
    private List<String> rules;
    private List<Map<String, Object>> csvData;

    @Override
    public String toString() {
        return "AuditRequestDTO{" +
                "auditType='" + auditType + '\'' +
                ", sector='" + sector + '\'' +
                ", regulations=" + regulations +
                ", rules=" + rules +
                ", csvData=" + csvData +
                '}';
    }
}
