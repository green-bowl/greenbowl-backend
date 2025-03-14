package com.greenbowl.greenbowlserver.recipe.port.in.usecase;

import com.greenbowl.greenbowlserver.recipe.port.in.command.CreateDetailedRecipeCommand;
import com.greenbowl.greenbowlserver.recipe.port.in.command.CreateRecipeCommand;

public interface CreateRecipeUseCase {
    Long createRecipe(CreateRecipeCommand createRecipeCommand);

    void createRecipe(CreateDetailedRecipeCommand createDetailedRecipeCommand);
}
