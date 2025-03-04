package com.greenbowl.greenbowlserver.recommendation.adapter.in.web.response;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;

public class ResponseGenerator {
    public static ResponseEntity<Flux<String>> generateResponse(Flux<String> responseFlux) {
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(responseFlux.flatMap(chunk -> Flux.just(chunk + "\n\n")));
    }
}
