package com.greenbowl.greenbowlserver.recommendation.adapter.out.web.client;

import com.greenbowl.greenbowlserver.recommendation.adapter.out.request.LlmRequest;
import com.greenbowl.greenbowlserver.recommendation.port.out.ReceiveLlmJsonPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;


@Component
@RequiredArgsConstructor
public class LlmJsonClient implements ReceiveLlmJsonPort {
    private final RestTemplate restTemplate;
    private static final String LLM_SERVER_URL = "https://hyobin-llm.duckdns.org";

    @Value("${llm.menu.type}")
    private String menuLlmType;

    @Value("${llm.menu.template}")
    private String menuTemplate;

    @Value("${llm.menu.secret-key}")
    private String menuSecretKey;

    @Value("${llm.server.endpoint.json}")
    private String requestEndpoint;

    @Override
    public String receiveLlmResponse(Object options, boolean isDetailed) {
        String url = String.format("%s/%s", LLM_SERVER_URL, requestEndpoint);

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON);

        String template = menusTemplate;
        LlmRequest llmRequest = LlmRequest.from(menuLlmType, menuTemplate, options, menuSecretKey);

        HttpEntity<LlmRequest> requestEntity = new HttpEntity<>(llmRequest, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        return responseEntity.getBody();
    }
}
