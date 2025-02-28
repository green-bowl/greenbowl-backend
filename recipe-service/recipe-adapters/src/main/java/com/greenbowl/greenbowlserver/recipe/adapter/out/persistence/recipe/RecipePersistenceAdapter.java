package com.greenbowl.greenbowlserver.recipe.adapter.out.persistence.recipe;

import com.greenbowl.greenbowlserver.common.adapter.out.PersistenceAdapter;
import com.greenbowl.greenbowlserver.recipe.domain.Recipe;
import com.greenbowl.greenbowlserver.recipe.port.out.SaveRecipePort;
import lombok.RequiredArgsConstructor;


@PersistenceAdapter
@RequiredArgsConstructor
public class RecipePersistenceAdapter implements SaveRecipePort {
    private final RecipeRepository recipeRepository;

    @Override
    public void saveRecipe(Recipe recipe) {
        recipeRepository.save(RecipeJpaEntity.from(recipe));
    }
}
