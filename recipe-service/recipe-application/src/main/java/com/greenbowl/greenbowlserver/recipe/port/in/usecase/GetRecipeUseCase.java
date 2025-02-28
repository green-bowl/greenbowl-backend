package com.greenbowl.greenbowlserver.recipe.port.in.usecase;

import com.greenbowl.greenbowlserver.recipe.domain.Recipe;

import java.util.List;

public interface GetRecipeUseCase {
    Recipe getRecipe(Long id);

    List<Recipe> getRecipe(String name);
}
