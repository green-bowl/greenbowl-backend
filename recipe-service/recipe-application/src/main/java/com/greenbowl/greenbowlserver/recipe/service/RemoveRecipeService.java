package com.greenbowl.greenbowlserver.recipe.service;

import com.greenbowl.greenbowlserver.common.application.UseCase;
import com.greenbowl.greenbowlserver.recipe.domain.Recipe;
import com.greenbowl.greenbowlserver.recipe.port.in.usecase.GetRecipeUseCase;
import com.greenbowl.greenbowlserver.recipe.port.in.usecase.RemoveRecipeUseCase;
import com.greenbowl.greenbowlserver.recipe.port.out.DeleteRecipePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;

@RequiredArgsConstructor
@UseCase
@Transactional(isolation = READ_UNCOMMITTED, timeout = 10)
public class RemoveRecipeService implements RemoveRecipeUseCase {
    private final GetRecipeUseCase getRecipeUseCase;
    private final DeleteRecipePort deleteRecipePort;

    @Override
    public void removeRecipe(String name) {
        List<Recipe> recipes = getRecipeUseCase.getRecipes(name);

        if (recipes.size() == 1) {
            deleteRecipePort.deleteById(recipes.get(0).getId());
            return;
        }

        deleteRecipePort.deleteByName(name);
    }
}
