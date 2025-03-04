package com.greenbowl.greenbowlserver.recommendation.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.recommendation.adapter.in.web.mapper.RecommendationRequestToCommandMapper;
import com.greenbowl.greenbowlserver.recommendation.adapter.in.web.request.RecipeOptionsRequest;
import com.greenbowl.greenbowlserver.recommendation.adapter.in.web.response.ResponseGenerator;
import com.greenbowl.greenbowlserver.recommendation.adapter.in.web.response.StreamingProcessor;
import com.greenbowl.greenbowlserver.recommendation.port.in.usecase.RecommendLlmRecipeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class RecommendLlmRecipeController {
    private final RecommendLlmRecipeUseCase recommendLlmRecipeUseCase;

    @GetMapping(value = "/recipes/sse", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Flux<String>> requestRecipeLLM(
            @RequestParam List<String> name,
            @RequestParam List<String> usedIngredientNames,
            @RequestParam List<String> usedIngredientWeights,
            @RequestParam List<String> cookingTime,
            @RequestParam List<String> calories
    ) {
        Flux<String> responseFlux = recommendLlmRecipeUseCase.recieveLLMRecommendedRecipe(
                RecommendationRequestToCommandMapper.mapToCommand(
                        RecipeOptionsRequest.of(name, usedIngredientNames, usedIngredientWeights, cookingTime, calories)
                )
        );

        StreamingProcessor.proceedStreaming(responseFlux);

        return ResponseGenerator.generateResponse(responseFlux);
    }
}
