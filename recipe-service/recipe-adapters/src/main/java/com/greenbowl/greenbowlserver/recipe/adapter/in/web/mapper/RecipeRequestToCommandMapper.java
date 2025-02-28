package com.greenbowl.greenbowlserver.recipe.adapter.in.web.mapper;

import com.greenbowl.greenbowlserver.common.utility.FormatConverter;
import com.greenbowl.greenbowlserver.recipe.adapter.in.web.request.AddRecipeRequest;
import com.greenbowl.greenbowlserver.recipe.port.in.command.CreateRecipeCommand;

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
}
