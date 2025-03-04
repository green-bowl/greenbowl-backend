package com.greenbowl.greenbowlserver.recommendation.adapter.in.web.request;

import lombok.Getter;

import java.util.List;

@Getter
public class RecipeOptionsRequest {
    private List<String> name;
    private List<String> usedIngredientNames;
    private List<String> usedIngredientWeights;
    private List<String> cookingTime;
    private List<String> calories;


    private RecipeOptionsRequest(
            List<String> name, List<String> usedIngredientNames, List<String> usedIngredientWeights,
            List<String> cookingTime, List<String> calories
    ) {
        this.name = name;
        this.usedIngredientNames = usedIngredientNames;
        this.usedIngredientWeights = usedIngredientWeights;
        this.cookingTime = cookingTime;
        this.calories = calories;
    }

    public static RecipeOptionsRequest of(
            List<String> name, List<String> usedIngredientNames, List<String> usedIngredientWeights,
            List<String> cookingTime, List<String> calories
    ) {
        return new RecipeOptionsRequest(name, usedIngredientNames, usedIngredientWeights, cookingTime, calories);
    }
}
