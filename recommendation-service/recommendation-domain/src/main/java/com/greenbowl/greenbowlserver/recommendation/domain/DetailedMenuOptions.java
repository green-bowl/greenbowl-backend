package com.greenbowl.greenbowlserver.recommendation.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class DetailedMenuOptions {
    private List<String> name;
    private List<String> availableIngredients;
    private List<String> cookingTime;
    private List<String> calories;

    public DetailedMenuOptions(
            List<String> name, List<String> availableIngredients, List<String> cookingTime, List<String> calories
    ) {
        this.name = name;
        this.availableIngredients = availableIngredients;
        this.cookingTime = cookingTime;
        this.calories = calories;
    }

    public static DetailedMenuOptions of(
            List<String> name, List<String> availableIngredients, List<String> cookingTime, List<String> calories
    ) {
        return new DetailedMenuOptions(name, availableIngredients, cookingTime, calories);
    }
}
