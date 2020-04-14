package com.small.missionboard.util;


import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public class UrlUtils {
    public static String addParameterList(String url, Map<String, String> params) {
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromUriString(url);
        params.forEach(urlBuilder::queryParam);
        return urlBuilder.build().toString();
    }

    public static String addParameter(String url, String name, String value) {
        return UriComponentsBuilder
                .fromUriString(url)
                .queryParam(name, value)
                .build()
                .toString();
    }
}