package com.greenbowl.greenbowlserver.recipe.port.in.mapper;

import com.greenbowl.greenbowlserver.recipe.domain.Recipe;
import com.greenbowl.greenbowlserver.recipe.port.in.command.CreateRecipeCommand;

public class RecipeCommandToDomainMapper {
    public static Recipe mapToDomainEntity(CreateRecipeCommand createRecipeCommand) {
        return Recipe.of(
                createRecipeCommand.getName(),
                createRecipeCommand.getImageUrl(),
                createRecipeCommand.getCookingTime(),
                createRecipeCommand.getCalories()
        );
    }
}
