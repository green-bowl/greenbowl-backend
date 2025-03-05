package com.greenbowl.greenbowlserver.recommendation.port.in.command;

import lombok.Getter;

import java.util.List;

@Getter
public class DetailedMenuOptionsCommand {
    private List<String> name;
    private List<String> availableIngredients;
    private List<String> cookingTime;
    private List<String> calories;

    public DetailedMenuOptionsCommand(
            List<String> name, List<String> availableIngredients, List<String> cookingTime, List<String> calories
    ) {
        this.name = name;
        this.availableIngredients = availableIngredients;
        this.cookingTime = cookingTime;
        this.calories = calories;
    }

    public static DetailedMenuOptionsCommand of(
            List<String> name, List<String> availableIngredients, List<String> cookingTime, List<String> calories
    ) {
        return new DetailedMenuOptionsCommand(name, availableIngredients, cookingTime, calories);
    }
}
