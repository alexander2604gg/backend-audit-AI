package com.portafolio.auditai.enums;

import lombok.Getter;

@Getter
public enum EnumValueModel {
    DEEPSEEK_MODEL("deepseek-chat");

    private final String model;

    private EnumValueModel(String model) {
        this.model = model;
    }

}