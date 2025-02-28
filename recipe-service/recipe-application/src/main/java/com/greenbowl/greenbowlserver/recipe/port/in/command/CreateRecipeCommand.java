package com.greenbowl.greenbowlserver.recipe.port.in.command;


import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateRecipeCommand {
    private Long userId;
    private String name;
    private String imageUrl;
    private short cookingTime;
    private short calories;

    @Builder
    private CreateRecipeCommand(
            Long userId, String name, String imageUrl, short cookingTime, short calories
    ) {
        this.userId = userId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.cookingTime = cookingTime;
        this.calories = calories;
    }

    public static CreateRecipeCommand of(
            Long userId, String name, String imageUrl, short cookingTime, short calories
    ) {
        return CreateRecipeCommand.builder()
                .userId(userId)
                .name(name)
                .imageUrl(imageUrl)
                .cookingTime(cookingTime)
                .calories(calories)
                .build();
    }
}
