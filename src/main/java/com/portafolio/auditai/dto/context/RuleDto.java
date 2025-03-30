package com.portafolio.auditai.dto.context;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RuleDto {

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("descripcion")
    private String descripcion;

    @JsonProperty("normativaRelacionada")
    private String normativaRelacionada;

    @JsonProperty("severidad")
    private String severidad;

    @JsonProperty("camposInvolucrados")
    private List<String> camposInvolucrados;
}
