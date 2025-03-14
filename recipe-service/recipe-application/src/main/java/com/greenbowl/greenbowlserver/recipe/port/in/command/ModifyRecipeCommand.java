package com.greenbowl.greenbowlserver.recipe.port.in.command;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ModifyRecipeCommand {
    private Long userId;
    private Long id;
    private String oneLineIntroduction;
    private String introduction;
    private List<IngredientCommand> ingredients;
    private NutritionCommand nutrition;

    @Builder
    private ModifyRecipeCommand(
            Long userId, Long id, String oneLineIntroduction, String introduction,
            List<IngredientCommand> ingredients, NutritionCommand nutrition
    ) {
        this.userId = userId;
        this.id = id;
        this.oneLineIntroduction = oneLineIntroduction;
        this.introduction = introduction;
        this.ingredients = ingredients;
        this.nutrition = nutrition;
    }

    public static ModifyRecipeCommand of(
            Long userId, Long id, String oneLineIntroduction, String introduction,
            List<IngredientCommand> ingredients, NutritionCommand nutrition
    ) {
        return ModifyRecipeCommand.builder()
                .userId(userId)
                .id(id)
                .oneLineIntroduction(oneLineIntroduction)
                .introduction(introduction)
                .ingredients(ingredients)
                .nutrition(nutrition)
                .build();
    }
}
