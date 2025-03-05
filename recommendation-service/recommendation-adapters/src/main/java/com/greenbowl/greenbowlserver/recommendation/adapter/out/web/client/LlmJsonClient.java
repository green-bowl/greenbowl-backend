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

    @Value("${llm.detailed-menu.type}")
    private String detailedMenuLlmType;

    @Value("${llm.detailed-menu.template}")
    private String detailedMenuTemplate;

    @Value("${llm.detailed-menu.secret-key}")
    private String detailedMenuSecretKey;

    @Value("${llm.server.endpoint.json}")
    private String requestEndpoint;

    @Override
    public String receiveLlmResponse(Object options, boolean isDetailed) {
        String url = String.format("%s/%s", LLM_SERVER_URL, requestEndpoint);

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON);

        String llmType = chooseLlmType(isDetailed);
        String template = chooseTemplate(isDetailed);
        String secretKey = chooseSecretKey(isDetailed);

        LlmRequest llmRequest = LlmRequest.from(llmType, template, options, secretKey);

        HttpEntity<LlmRequest> requestEntity = new HttpEntity<>(llmRequest, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        return responseEntity.getBody();
    }

    private String chooseLlmType(boolean isDetailed) {
        if (isDetailed) {
            return detailedMenuLlmType;
        }

        return menuLlmType;
    }

    private String chooseTemplate(boolean isDetailed) {
        if (isDetailed) {
            return detailedMenuTemplate;
        }

        return menuTemplate;
    }

    private String chooseSecretKey(boolean isDetailed) {
        if (isDetailed) {
            return detailedMenuSecretKey;
        }

        return menuSecretKey;
    }
}
