package com.greenbowl.greenbowlserver.recommendation.port.out;

import com.greenbowl.greenbowlserver.recommendation.domain.RecipeOptions;
import reactor.core.publisher.Flux;

public interface ReceiveLLMStreamingPort {
    Flux<String> receiveLLMStreamingSSE(RecipeOptions recipeOptions);
}
