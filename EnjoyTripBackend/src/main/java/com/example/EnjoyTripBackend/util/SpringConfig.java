package com.example.EnjoyTripBackend.util;

import com.example.EnjoyTripBackend.exception.EnjoyTripException;
import com.example.EnjoyTripBackend.exception.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class SpringConfig {

    @Value("${server.connection-timeout.second}")
    private int connectionTimeout;

    @Value("${server.response-timeout.second}")
    private int responseTimeout;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(connectionTimeout))
                .setReadTimeout(Duration.ofSeconds(responseTimeout))
                .build();
    }

    public ClientHttpRequestInterceptor clientHttpRequestInterceptor() {
        return (request, body, execution) -> {
            RetryTemplate retryTemplate = new RetryTemplate();
            retryTemplate.setRetryPolicy(new SimpleRetryPolicy(3));
            try {
                return retryTemplate.execute(context -> execution.execute(request, body));
            } catch (Exception e) {
                throw new EnjoyTripException(ErrorCode.ANONYMOUS_USER);
            }
        };
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public HttpHeaders httpHeaders(){
        return new HttpHeaders();
    }
}