package com.greenbowl.greenbowlserver.recommendation.port.in.command;


import lombok.Getter;

import java.util.List;

@Getter
public class MenuOptionsCommand {
    private List<String> ingredients;
    private List<String> cookingTimeLimit;
    private List<String> cuisineType;

    private MenuOptionsCommand(List<String> ingredients, List<String> cookingTimeLimit, List<String> cuisineType) {
        this.ingredients = ingredients;
        this.cookingTimeLimit = cookingTimeLimit;
        this.cuisineType = cuisineType;
    }

    public static MenuOptionsCommand of(
            List<String> ingredients, List<String> cookingTimeLimit, List<String> cuisineType
    ) {
        return new MenuOptionsCommand(ingredients, cookingTimeLimit, cuisineType);
    }
}
