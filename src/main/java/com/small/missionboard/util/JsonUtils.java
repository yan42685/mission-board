package com.small.missionboard.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public class JsonUtils {
    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    public static String object2Json(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }

    public static <T> T json2Object(String jsonStr, Class<T> objClass) throws JsonProcessingException {
        return mapper.readValue(jsonStr, objClass);
    }

    public static Object json2Object(String jsonStr) throws JsonProcessingException {
        return mapper.readValue(jsonStr, Object.class);
    }
}
