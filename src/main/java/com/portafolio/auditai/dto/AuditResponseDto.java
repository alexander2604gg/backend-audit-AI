package com.portafolio.auditai.dto;

import lombok.Data;

import java.util.List;
@Data
public class AuditResponseDto {

    private String title;
    private String typeGrafic;
    private ChartData chartData;
    private String description;

    @Data
    public static class ChartData {
        private List<String> labels;
        private List<Dataset> datasets;
    }

    @Data
    public static class Dataset {
        private String label;
        private List<Integer> data;
        private List<String> backgroundColor;
        private List<String> borderColor;
        private int borderWidth;
    }

}
