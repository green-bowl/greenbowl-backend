package com.greenbowl.greenbowlserver.recipe.adapter.in.web.mapper;

import com.greenbowl.greenbowlserver.common.utility.FormatConverter;
import com.greenbowl.greenbowlserver.recipe.adapter.in.web.request.AddDetailedRecipeRequest;
import com.greenbowl.greenbowlserver.recipe.adapter.in.web.request.AddRecipeRequest;
import com.greenbowl.greenbowlserver.recipe.adapter.in.web.request.ModifyRecipeRequest;
import com.greenbowl.greenbowlserver.recipe.adapter.in.web.request.NutritionRequest;
import com.greenbowl.greenbowlserver.recipe.port.in.command.*;

import java.util.stream.Collectors;

public class RecipeRequestToCommandMapper {
    public static CreateRecipeCommand mapToCommand(String userId, AddRecipeRequest addRecipeRequest) {
        return CreateRecipeCommand.of(
                FormatConverter.parseToLong(userId),
                addRecipeRequest.getName(),
                addRecipeRequest.getImageUrl(),
                FormatConverter.parseToShort(addRecipeRequest.getCookingTime()),
                FormatConverter.parseToShort(addRecipeRequest.getCalories())
        );
    }

    public static CreateDetailedRecipeCommand mapToCommand(String userId, AddDetailedRecipeRequest addDetailedRecipeRequest) {
        return CreateDetailedRecipeCommand.of(
                FormatConverter.parseToLong(userId),
                addDetailedRecipeRequest.getName(),
                addDetailedRecipeRequest.getImageUrl(),
                FormatConverter.parseToShort(addDetailedRecipeRequest.getCookingTime()),
                FormatConverter.parseToShort(addDetailedRecipeRequest.getCalories()),
                addDetailedRecipeRequest.getOneLineIntroduction(),
                addDetailedRecipeRequest.getIntroduction(),
                addDetailedRecipeRequest.getIngredients()
                        .stream()
                        .map(
                                ingredient -> IngredientCommand.of(
                                        ingredient.getName(), FormatConverter.parseToShort(ingredient.getWeight())
                                )
                        )
                        .collect(Collectors.toList()),
                mapToCommand(addDetailedRecipeRequest.getNutrition())
        );
    }

    public static ModifyRecipeCommand mapToCommand(String userId, ModifyRecipeRequest modifyRecipeRequest) {
        return ModifyRecipeCommand.of(
                FormatConverter.parseToLong(userId),
                FormatConverter.parseToLong(modifyRecipeRequest.getId()),
                modifyRecipeRequest.getOneLineIntroduction(),
                modifyRecipeRequest.getIntroduction(),
                modifyRecipeRequest.getIngredients()
                        .stream()
                        .map(
                                ingredient -> IngredientCommand.of(
                                        ingredient.getName(), FormatConverter.parseToShort(ingredient.getWeight())
                                )
                        )
                        .collect(Collectors.toList()),
                mapToCommand(modifyRecipeRequest.getNutrition())
        );
    }

    private static NutritionCommand mapToCommand(NutritionRequest nutritionRequest) {
        return NutritionCommand.of(
                FormatConverter.parseToShort(nutritionRequest.getCarbohydrate()),
                FormatConverter.parseToShort(nutritionRequest.getProtein()),
                FormatConverter.parseToShort(nutritionRequest.getFat()),
                FormatConverter.parseToShort(nutritionRequest.getSodium()),
                FormatConverter.parseToShort(nutritionRequest.getSugar())
        );
    }
}
