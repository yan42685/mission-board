package com.small.missionboard.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.SneakyThrows;

public class JsonUtils {
    private static ObjectMapper mapper;
    static {
        mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }



    @SneakyThrows
    public static String object2Json(Object obj) {
        return mapper.writeValueAsString(obj);
    }

    @SneakyThrows
    public static <T> T json2Object(String jsonStr, Class<T> objClass) {
        return mapper.readValue(jsonStr, objClass);
    }

    @SneakyThrows
    public static Object json2Object(String jsonStr) {
        return mapper.readValue(jsonStr, Object.class);
    }
}
