package com.greenbowl.greenbowlserver.recommendation.adapter.out.web.client;

import com.greenbowl.greenbowlserver.recommendation.adapter.out.web.LLMRequest;
import com.greenbowl.greenbowlserver.recommendation.domain.RecipeOptions;
import com.greenbowl.greenbowlserver.recommendation.port.out.ReceiveLLMStreamingPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class LLMStreamingClient implements ReceiveLLMStreamingPort {
    private final WebClient webClient;
    private static final String LLM_SERVER_URL = "https://hyobin-llm.duckdns.org";

    public LLMStreamingClient() {
        webClient = WebClient.create(LLM_SERVER_URL);
    }

    @Value("${llm.recipe.type}")
    private String llmType;

    @Value("${llm.recipe.template}")
    private String template;

    @Value("${llm.recipe.secret-key}")
    private String secretKey;

    @Value("${llm.server.endpoint.sse}")
    private String sseRequestEndpoint;

    @Override
    public Flux<String> receiveLLMStreamingSSE(RecipeOptions recipeOptions) {
        LLMRequest llmRequest = LLMRequest.from(llmType, template, recipeOptions, secretKey);

        return webClientRequest(sseRequestEndpoint, llmRequest);
    }

    private Flux<String> webClientRequest(String requestEndpoint, LLMRequest llmRequest) {
        return webClient.post()
                .uri(requestEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .bodyValue(llmRequest)
                .retrieve()
                .bodyToFlux(DataBuffer.class)
                .map(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    return new String(bytes);
                });
    }
}
