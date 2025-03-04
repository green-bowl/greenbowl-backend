package com.greenbowl.greenbowlserver.recommendation.port.in.usecase;


import com.greenbowl.greenbowlserver.recommendation.port.in.command.RecipeOptionsCommand;
import reactor.core.publisher.Flux;

public interface RecommendLlmRecipeUseCase {
    Flux<String> recieveLLMRecommendedRecipe(RecipeOptionsCommand recipeOptionsCommand);
}
