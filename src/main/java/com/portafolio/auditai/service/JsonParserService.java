package com.portafolio.auditai.service;

import java.util.List;

public interface JsonParserService {

    <T> T toObject (String json , Class<T> object);
    <T> List<T> toList(String json, Class<T> objectType);
    String extractJson(String rawResponse);

}
