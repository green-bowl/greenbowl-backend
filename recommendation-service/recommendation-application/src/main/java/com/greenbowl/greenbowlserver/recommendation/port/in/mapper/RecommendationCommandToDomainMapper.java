package com.greenbowl.greenbowlserver.recommendation.port.in.mapper;

import com.greenbowl.greenbowlserver.recommendation.domain.DetailedMenuOptions;
import com.greenbowl.greenbowlserver.recommendation.domain.MenuOptions;
import com.greenbowl.greenbowlserver.recommendation.domain.RecipeOptions;
import com.greenbowl.greenbowlserver.recommendation.port.in.command.DetailedMenuOptionsCommand;
import com.greenbowl.greenbowlserver.recommendation.port.in.command.MenuOptionsCommand;
import com.greenbowl.greenbowlserver.recommendation.port.in.command.RecipeOptionsCommand;

public class RecommendationCommandToDomainMapper {
    public static RecipeOptions mapToDomainEntity(RecipeOptionsCommand recipeOptionsCommand) {
        return RecipeOptions.of(
                recipeOptionsCommand.getName(),
                recipeOptionsCommand.getUsedIngredientNames(),
                recipeOptionsCommand.getUsedIngredientWeights(),
                recipeOptionsCommand.getCookingTime(),
                recipeOptionsCommand.getCalories()
        );
    }

    public static MenuOptions mapToDomainEntity(MenuOptionsCommand menuOptionsCommand) {
        return MenuOptions.of(
                menuOptionsCommand.getIngredients(),
                menuOptionsCommand.getCookingTimeLimit(),
                menuOptionsCommand.getCookingTimeLimit()
        );
    }

    public static DetailedMenuOptions mapToDomainEntity(DetailedMenuOptionsCommand detailedMenuOptionsCommand) {
        return DetailedMenuOptions.of(
                detailedMenuOptionsCommand.getName(),
                detailedMenuOptionsCommand.getAvailableIngredients(),
                detailedMenuOptionsCommand.getCookingTime(),
                detailedMenuOptionsCommand.getCalories()
        );
    }
}
