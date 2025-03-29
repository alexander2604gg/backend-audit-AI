package com.portafolio.auditai.enums;

import lombok.Getter;

@Getter
public enum EnumValueRole {
    SYSTEM("system"),
    USER("User");

    private final String role;

    EnumValueRole(String role) {
        this.role = role;
    }
}
