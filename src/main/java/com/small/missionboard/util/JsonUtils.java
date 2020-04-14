package com.small.missionboard.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class JsonUtils {
    private static ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    public static String bean2Json(Object obj) {
        return mapper.writeValueAsString(obj);
    }

    @SneakyThrows
    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        return mapper.readValue(jsonStr, objClass);
    }
}
