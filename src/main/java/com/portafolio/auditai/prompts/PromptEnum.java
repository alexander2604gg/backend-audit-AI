package com.portafolio.auditai.prompts;

public enum PromptEnum {

    RULES_RECOMMENDATION("");
    private final String message;
    PromptEnum(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
