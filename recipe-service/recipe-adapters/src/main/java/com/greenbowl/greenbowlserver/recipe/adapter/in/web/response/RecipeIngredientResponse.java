package com.greenbowl.greenbowlserver.recipe.adapter.in.web.response;

import com.greenbowl.greenbowlserver.common.utility.FormatValidator;
import com.greenbowl.greenbowlserver.recipe.domain.RecipeIngredient;
import lombok.Getter;

@Getter
public class RecipeIngredientResponse {
    private String name;
    private short weight;

    private RecipeIngredientResponse(String name, short weight) {
        this.name = name;
        this.weight = weight;
    }

    public static RecipeIngredientResponse from(RecipeIngredient recipeIngredient) {
        if (FormatValidator.hasValue(recipeIngredient)) {
            return new RecipeIngredientResponse(recipeIngredient.getName(), recipeIngredient.getWeight());
        }

        return null;
    }
}
