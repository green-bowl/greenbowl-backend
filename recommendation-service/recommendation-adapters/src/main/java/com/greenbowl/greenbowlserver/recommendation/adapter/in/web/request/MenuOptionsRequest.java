package com.greenbowl.greenbowlserver.recommendation.adapter.in.web.request;

import lombok.Getter;

import java.util.List;

@Getter
public class MenuOptionsRequest {
    private List<String> ingredients;
    private List<String> cookingTimeLimit;
    private List<String> cuisineType;

    private MenuOptionsRequest(List<String> ingredients, List<String> cookingTimeLimit, List<String> cuisineType) {
        this.ingredients = ingredients;
        this.cookingTimeLimit = cookingTimeLimit;
        this.cuisineType = cuisineType;
    }

    public static MenuOptionsRequest of(
            List<String> ingredients, List<String> cookingTimeLimit, List<String> cuisineType
    ) {
        return new MenuOptionsRequest(ingredients, cookingTimeLimit, cuisineType);
    }
}
