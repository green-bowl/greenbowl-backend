package com.greenbowl.greenbowlserver.recipe.port.in.usecase;

public interface RemoveRecipeUseCase {
    void removeRecipe(Long id);

    void removeRecipe(String name);
}
