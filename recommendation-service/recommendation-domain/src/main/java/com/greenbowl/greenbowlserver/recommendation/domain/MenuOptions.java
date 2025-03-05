package com.greenbowl.greenbowlserver.recommendation.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MenuOptions {
    private List<String> ingredients;
    private List<String> cookingTimeLimit;
    private List<String> cuisineType;

    @Builder
    private MenuOptions(List<String> ingredients, List<String> cookingTimeLimit, List<String> cuisineType) {
        this.ingredients = ingredients;
        this.cookingTimeLimit = cookingTimeLimit;
        this.cuisineType = cuisineType;
    }

    public static MenuOptions of(
            List<String> ingredients, List<String> cookingTimeLimit, List<String> cuisineType
    ) {
        return new MenuOptions(ingredients, cookingTimeLimit, cuisineType);
    }
}
