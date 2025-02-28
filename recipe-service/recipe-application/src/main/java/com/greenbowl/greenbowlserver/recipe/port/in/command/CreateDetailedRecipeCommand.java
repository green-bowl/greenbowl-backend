package com.greenbowl.greenbowlserver.recipe.port.in.command;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateDetailedRecipeCommand {
    private Long userId;
    private String name;
    private String imageUrl;
    private short cookingTime;
    private short calories;
    private String oneLineIntroduction;
    private String introduction;
    private List<IngredientCommand> ingredients;
    private NutritionCommand nutrition;

    @Builder
    private CreateDetailedRecipeCommand(
            Long userId, String name, String imageUrl, short cookingTime, short calories, String oneLineIntroduction,
            String introduction, List<IngredientCommand> ingredients, NutritionCommand nutrition
    ) {
        this.userId = userId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.cookingTime = cookingTime;
        this.calories = calories;
        this.oneLineIntroduction = oneLineIntroduction;
        this.introduction = introduction;
        this.ingredients = ingredients;
        this.nutrition = nutrition;
    }

    public static CreateDetailedRecipeCommand of(
            Long userId, String name, String imageUrl, short cookingTime, short calories, String oneLineIntroduction,
            String introduction, List<IngredientCommand> ingredients, NutritionCommand nutrition
    ) {
        return CreateDetailedRecipeCommand.builder()
                .userId(userId)
                .name(name)
                .imageUrl(imageUrl)
                .cookingTime(cookingTime)
                .calories(calories)
                .oneLineIntroduction(oneLineIntroduction)
                .introduction(introduction)
                .ingredients(ingredients)
                .nutrition(nutrition)
                .build();
    }
}
