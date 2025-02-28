package com.greenbowl.greenbowlserver.recipe.port.out;

import com.greenbowl.greenbowlserver.recipe.domain.Recipe;

public interface SaveRecipePort {
    void saveRecipe(Recipe recipe);
}
