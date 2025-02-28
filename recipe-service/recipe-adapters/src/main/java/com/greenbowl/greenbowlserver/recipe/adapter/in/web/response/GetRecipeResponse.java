package com.greenbowl.greenbowlserver.recipe.adapter.in.web.response;

import com.greenbowl.greenbowlserver.recipe.domain.Recipe;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetRecipeResponse {
    private Long id;
    private String name;
    private String imageUrl;
    private short cookingTime;
    private short calories;
    private String oneLineIntroduction;
    private List<RecipeIngredientResponse> recipeIngredients;
    private String introduction;
    private NutritionResponse nutrition;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    @Builder
    private GetRecipeResponse(
            Long id, String name, String imageUrl, short cookingTime, short calories,
            String oneLineIntroduction, List<RecipeIngredientResponse> recipeIngredients,
            String introduction, NutritionResponse nutrition, LocalDateTime createdAt, LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.cookingTime = cookingTime;
        this.calories = calories;
        this.oneLineIntroduction = oneLineIntroduction;
        this.recipeIngredients = recipeIngredients;
        this.introduction = introduction;
        this.nutrition = nutrition;
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
                .oneLineIntroduction(recipe.getOneLineIntroduction())
                .recipeIngredients(
                        recipe.getRecipeIngredients()
                                .stream()
                                .map(RecipeIngredientResponse::from)
                                .collect(Collectors.toList())
                )
                .introduction(recipe.getIntroduction())
                .nutrition(NutritionResponse.from(recipe.getNutrition()))
                .createdAt(recipe.getCreatedAt())
                .modifiedAt(recipe.getModifiedAt())
                .build();
    }
}
