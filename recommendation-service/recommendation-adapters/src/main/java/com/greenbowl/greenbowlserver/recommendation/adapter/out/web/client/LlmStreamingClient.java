package com.greenbowl.greenbowlserver.recommendation.adapter.out.web.client;

import com.greenbowl.greenbowlserver.recommendation.adapter.out.request.LlmRequest;
import com.greenbowl.greenbowlserver.recommendation.port.out.ReceiveLlmStreamingPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.netty.http.client.PrematureCloseException;

@Component
@RequiredArgsConstructor
public class LlmStreamingClient implements ReceiveLlmStreamingPort {
    private final WebClient webClient;
    private static final Logger log = LoggerFactory.getLogger(LlmStreamingClient.class);

    @Value("${llm.recipe.type}")
    private String llmType;

    @Value("${llm.recipe.template}")
    private String template;

    @Value("${llm.recipe.secret-key}")
    private String secretKey;

    @Value("${llm.server.endpoint.sse}")
    private String sseRequestEndpoint;

    @Override
    public Flux<String> receiveLlmStreamingSSE(Object options) {
        LlmRequest llmRequest = LlmRequest.from(llmType, template, options, secretKey);

        return webClientRequest(sseRequestEndpoint, llmRequest);
    }

    private Flux<String> webClientRequest(String requestEndpoint, LlmRequest llmRequest) {
        return webClient.post()
                .uri(requestEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .bodyValue(llmRequest)
                .retrieve()
                .bodyToFlux(DataBuffer.class)
                .map(dataBuffer -> {
                    try {
                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(bytes);
                        return new String(bytes);
                    } finally {
                        DataBufferUtils.release(dataBuffer);
                    }
                })
                .doOnError(PrematureCloseException.class, e -> log.error("Ignoring PrematureCloseException and continuing SSE..."))
                .onErrorContinue((throwable, obj) -> {
                    if (throwable instanceof PrematureCloseException) {
                        log.warn("PrematureCloseException ignored, continuing SSE stream...");
                    } else {
                        log.error("Unexpected error in SSE stream", throwable);
                    }
                });
    }
}
