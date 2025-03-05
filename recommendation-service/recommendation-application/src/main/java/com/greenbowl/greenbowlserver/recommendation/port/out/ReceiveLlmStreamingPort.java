package com.greenbowl.greenbowlserver.recommendation.port.out;

import reactor.core.publisher.Flux;

public interface ReceiveLlmStreamingPort {
    Flux<String> receiveLlmStreamingSSE(Object options);
}
