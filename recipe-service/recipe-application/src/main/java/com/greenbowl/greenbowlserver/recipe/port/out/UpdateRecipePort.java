package com.greenbowl.greenbowlserver.recipe.port.out;

import com.greenbowl.greenbowlserver.recipe.domain.Recipe;

public interface UpdateRecipePort {
    void updateRecipe(Recipe recipe);
}
