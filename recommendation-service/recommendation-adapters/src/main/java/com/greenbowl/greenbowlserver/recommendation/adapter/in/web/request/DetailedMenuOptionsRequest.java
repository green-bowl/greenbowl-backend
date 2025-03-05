package com.greenbowl.greenbowlserver.recommendation.adapter.in.web.request;

import lombok.Getter;

import java.util.List;

@Getter
public class DetailedMenuOptionsRequest {
    private List<String> name;
    private List<String> availableIngredients;
    private List<String> cookingTime;
    private List<String> calories;

    public DetailedMenuOptionsRequest(
            List<String> name, List<String> availableIngredients, List<String> cookingTime, List<String> calories
    ) {
        this.name = name;
        this.availableIngredients = availableIngredients;
        this.cookingTime = cookingTime;
        this.calories = calories;
    }

    public static DetailedMenuOptionsRequest of(
            List<String> name, List<String> availableIngredients, List<String> cookingTime, List<String> calories
    ) {
        return new DetailedMenuOptionsRequest(name, availableIngredients, cookingTime, calories);
    }
}
