package com.greenbowl.greenbowlserver.recipe.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class Recipe {
    private String name;
    private String imageUrl;
    private short cookingTime;
    private short calories;
    private String oneLineIntroduction;
    private List<RecipIngredient> recipeIngredients;
    private String introduction;
    private Nutrition nutrition;

    @Builder
    private Recipe(
            String name, String imageUrl, short cookingTime, short calories, String oneLineIntroduction,
            List<RecipIngredient> recipeIngredients, String introduction, Nutrition nutrition
    ) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.cookingTime = cookingTime;
        this.calories = calories;
        this.oneLineIntroduction = oneLineIntroduction;
        this.recipeIngredients = recipeIngredients;
        this.introduction = introduction;
        this.nutrition = nutrition;
    }

    public static Recipe of(String name, String imageUrl, short cookingTime, short calories) {
        return Recipe.builder()
                .name(name)
                .imageUrl(imageUrl)
                .cookingTime(cookingTime)
                .calories(calories)
                .build();
    }
}
