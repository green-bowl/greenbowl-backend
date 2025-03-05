package com.greenbowl.greenbowlserver.recommendation.adapter.out.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import static com.greenbowl.greenbowlserver.recommendation.adapter.out.request.ApiConstant.LLM_SERVER_URL;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient() {
        return WebClient.create(LLM_SERVER_URL);
    }
}
