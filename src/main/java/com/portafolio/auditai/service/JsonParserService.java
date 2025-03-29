package com.portafolio.auditai.service;

public interface JsonParserService {

    <T> T toObject (String json , Class<T> object);
    String extractJson(String rawResponse);

}
