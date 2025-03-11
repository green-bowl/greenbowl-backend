package com.greenbowl.greenbowlserver.recommendation.service;

import com.greenbowl.greenbowlserver.common.application.UseCase;
import com.greenbowl.greenbowlserver.recommendation.port.in.command.RecipeOptionsCommand;
import com.greenbowl.greenbowlserver.recommendation.port.in.mapper.RecommendationCommandToDomainMapper;
import com.greenbowl.greenbowlserver.recommendation.port.in.usecase.RecommendLlmRecipeUseCase;
import com.greenbowl.greenbowlserver.recommendation.port.out.ReceiveLlmStreamingPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@UseCase
public class RecommendLlmRecipeService implements RecommendLlmRecipeUseCase {
    private final ReceiveLlmStreamingPort receiveLlmStreamingPort;

    @Override
    public Flux<String> recieveLlmRecommendedRecipe(RecipeOptionsCommand recipeOptionsCommand) {
        return receiveLlmStreamingPort.receiveLlmStreamingSSE(
                RecommendationCommandToDomainMapper.mapToDomainEntity(recipeOptionsCommand)
        );
    }
}
