package com.small.missionboard.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 用于调用 HTTP 接口
 */
@SpringBootConfiguration
public class RestTemplateConfig {
        @Bean
        public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
            SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
            factory.setConnectTimeout(15000);
            factory.setReadTimeout(5000);
            return factory;
        }

        @Bean
        public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
            return new RestTemplate(factory);
        }
}
