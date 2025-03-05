package com.greenbowl.greenbowlserver.recommendation.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class RecipeOptions {
    private List<String> name;
    private List<String> usedIngredientNames;
    private List<String> usedIngredientWeights;
    private List<String> cookingTime;
    private List<String> calories;

    @Builder
    private RecipeOptions(
            List<String> name, List<String> usedIngredientNames, List<String> usedIngredientWeights,
            List<String> cookingTime, List<String> calories
    ) {
        this.name = name;
        this.usedIngredientNames = usedIngredientNames;
        this.usedIngredientWeights = usedIngredientWeights;
        this.cookingTime = cookingTime;
        this.calories = calories;
    }

    public static RecipeOptions of(
            List<String> name, List<String> usedIngredientNames, List<String> usedIngredientWeights,
            List<String> cookingTime, List<String> calories
    ) {
        return RecipeOptions.builder()
                .name(name)
                .usedIngredientNames(usedIngredientNames)
                .usedIngredientWeights(usedIngredientWeights)
                .cookingTime(cookingTime)
                .calories(calories)
                .build();
    }
}
