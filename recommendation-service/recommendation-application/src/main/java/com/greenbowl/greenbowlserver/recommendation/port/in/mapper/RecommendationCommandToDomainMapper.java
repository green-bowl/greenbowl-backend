package com.greenbowl.greenbowlserver.recommendation.port.in.mapper;

import com.greenbowl.greenbowlserver.recommendation.domain.RecipeOptions;
import com.greenbowl.greenbowlserver.recommendation.port.in.command.RecipeOptionsCommand;

public class RecommendationCommandToDomainMapper {
    public static RecipeOptions mapToDomainEntity(RecipeOptionsCommand recipeOptionsCommand) {
        return RecipeOptions.of(
                recipeOptionsCommand.getName(),
                recipeOptionsCommand.getUsedIngredientNames(),
                recipeOptionsCommand.getUsedIngredientWeights(),
                recipeOptionsCommand.getCookingTime(),
                recipeOptionsCommand.getCalories()
        );
    }
}
