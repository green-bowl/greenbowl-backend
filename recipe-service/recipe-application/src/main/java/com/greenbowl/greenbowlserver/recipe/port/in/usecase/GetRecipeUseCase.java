package com.greenbowl.greenbowlserver.recipe.port.in.usecase;

import com.greenbowl.greenbowlserver.recipe.domain.Recipe;

import java.util.List;

public interface GetRecipeUseCase {
    List<Recipe> getRecipes(Long userId);

    List<Recipe> getRecipes(String name);

    Recipe getRecipe(Long id);
}
