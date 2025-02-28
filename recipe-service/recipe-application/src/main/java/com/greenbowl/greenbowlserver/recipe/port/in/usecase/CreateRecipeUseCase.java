package com.greenbowl.greenbowlserver.recipe.port.in.usecase;

import com.greenbowl.greenbowlserver.recipe.port.in.command.CreateRecipeCommand;

public interface CreateRecipeUseCase {
    void createRecipe(CreateRecipeCommand createRecipeCommand);
}
