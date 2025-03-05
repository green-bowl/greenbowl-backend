package com.greenbowl.greenbowlserver.recommendation.adapter.in.web.mapper;

import com.greenbowl.greenbowlserver.recommendation.adapter.in.web.request.MenuOptionsRequest;
import com.greenbowl.greenbowlserver.recommendation.adapter.in.web.request.RecipeOptionsRequest;
import com.greenbowl.greenbowlserver.recommendation.port.in.command.MenuOptionsCommand;
import com.greenbowl.greenbowlserver.recommendation.port.in.command.RecipeOptionsCommand;

public class RecommendationRequestToCommandMapper {
    public static RecipeOptionsCommand mapToCommand(RecipeOptionsRequest recipeOptionsRequest) {
        return RecipeOptionsCommand.of(
                recipeOptionsRequest.getName(),
                recipeOptionsRequest.getUsedIngredientNames(),
                recipeOptionsRequest.getUsedIngredientWeights(),
                recipeOptionsRequest.getCookingTime(),
                recipeOptionsRequest.getCalories()
        );
    }

    public static MenuOptionsCommand mapToCommand(MenuOptionsRequest menuOptionsRequest) {
        return MenuOptionsCommand.of(
                menuOptionsRequest.getIngredients(),
                menuOptionsRequest.getCookingTimeLimit(),
                menuOptionsRequest.getCuisineType()
        );
    }
}
