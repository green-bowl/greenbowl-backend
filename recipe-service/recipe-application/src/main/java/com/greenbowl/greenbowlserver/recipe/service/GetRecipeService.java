package com.greenbowl.greenbowlserver.recipe.service;

import com.greenbowl.greenbowlserver.common.application.UseCase;
import com.greenbowl.greenbowlserver.common.utility.FormatValidator;
import com.greenbowl.greenbowlserver.recipe.domain.Recipe;
import com.greenbowl.greenbowlserver.recipe.exception.RecipeNotFoundException;
import com.greenbowl.greenbowlserver.recipe.port.in.usecase.GetRecipeUseCase;
import com.greenbowl.greenbowlserver.recipe.port.out.FindRecipePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.greenbowl.greenbowlserver.recipe.exception.ExceptionMessage.RECIPE_ID_NOT_FOUND_EXCEPTION_MESSAGE;
import static com.greenbowl.greenbowlserver.recipe.exception.ExceptionMessage.RECIPE_NAME_NOT_FOUND_EXCEPTION_MESSAGE;
import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@RequiredArgsConstructor
@UseCase
@Transactional(isolation = READ_COMMITTED, readOnly = true, timeout = 15)
public class GetRecipeService implements GetRecipeUseCase {
    private final FindRecipePort findRecipePort;

    @Override
    public Recipe getRecipe(Long id) {
        return findRecipePort.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException(String.format(RECIPE_ID_NOT_FOUND_EXCEPTION_MESSAGE, id)));
    }

    @Override
    public List<Recipe> getRecipe(String name) {
        List<Recipe> recipes = findRecipePort.findByName(name);
        if (FormatValidator.hasValue(recipes)) {
            return recipes;
        }

        throw new RecipeNotFoundException(String.format(RECIPE_NAME_NOT_FOUND_EXCEPTION_MESSAGE, name));
    }
}
