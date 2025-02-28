package com.greenbowl.greenbowlserver.recipe.adapter.in.web.response;

import com.greenbowl.greenbowlserver.recipe.domain.Recipe;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetRecipeResponse {
    private Long id;
    private String name;
    private String imageUrl;
    private short cookingTime;
    private short calories;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    @Builder
    private GetRecipeResponse(
            Long id, String name, String imageUrl, short cookingTime, short calories,
            LocalDateTime createdAt, LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.cookingTime = cookingTime;
        this.calories = calories;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static GetRecipeResponse from(Recipe recipe) {
        return GetRecipeResponse.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .imageUrl(recipe.getImageUrl())
                .cookingTime(recipe.getCookingTime())
                .calories(recipe.getCalories())
                .createdAt(recipe.getCreatedAt())
                .modifiedAt(recipe.getModifiedAt())
                .build();
    }
}
