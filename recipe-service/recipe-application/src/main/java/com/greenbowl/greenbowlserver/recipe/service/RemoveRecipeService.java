package com.greenbowl.greenbowlserver.recipe.service;

import com.greenbowl.greenbowlserver.common.application.UseCase;
import com.greenbowl.greenbowlserver.recipe.domain.Recipe;
import com.greenbowl.greenbowlserver.recipe.exception.RecipeNotFoundException;
import com.greenbowl.greenbowlserver.recipe.port.in.usecase.GetRecipeUseCase;
import com.greenbowl.greenbowlserver.recipe.port.in.usecase.RemoveRecipeUseCase;
import com.greenbowl.greenbowlserver.recipe.port.out.DeleteRecipePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.greenbowl.greenbowlserver.recipe.exception.ExceptionMessage.RECIPE_ID_NOT_FOUND_EXCEPTION_MESSAGE;
import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;

@RequiredArgsConstructor
@UseCase
@Transactional(isolation = READ_UNCOMMITTED, timeout = 10)
public class RemoveRecipeService implements RemoveRecipeUseCase {
    private final GetRecipeUseCase getRecipeUseCase;
    private final DeleteRecipePort deleteRecipePort;

    @Override
    public void removeRecipe(Long id) {
        if (getRecipeUseCase.isExistent(id)) {
            deleteRecipePort.deleteById(id);
            return;
        }

        throw new RecipeNotFoundException(String.format(RECIPE_ID_NOT_FOUND_EXCEPTION_MESSAGE, id));
    }

    @Override
    public void removeRecipe(String name) {
        List<Recipe> recipes = getRecipeUseCase.getRecipes(name);

        deleteRecipePort.deleteById(recipes.get(0).getId());
    }
}
