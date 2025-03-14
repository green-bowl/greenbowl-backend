package com.greenbowl.greenbowlserver.recipe.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Recipe {
    private Long id;
    private Long userId;
    private String name;
    private String imageUrl;
    private short cookingTime;
    private short calories;
    private String oneLineIntroduction;
    private List<RecipeIngredient> recipeIngredients;
    private String introduction;
    private Nutrition nutrition;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    private Recipe(
            Long id, Long userId, String name, String imageUrl, short cookingTime, short calories,
            String oneLineIntroduction, List<RecipeIngredient> recipeIngredients, String introduction,
            Nutrition nutrition, LocalDateTime createdAt, LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.userId = userId;
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

    public static Recipe of(Long userId, String name, String imageUrl, short cookingTime, short calories) {
        return Recipe.builder()
                .userId(userId)
                .name(name)
                .imageUrl(imageUrl)
                .cookingTime(cookingTime)
                .calories(calories)
                .build();
    }

    public static Recipe of(
            Long userId, String name, String imageUrl, short cookingTime, short calories, String oneLineIntroduction,
            List<RecipeIngredient> recipeIngredients, String introduction, Nutrition nutrition
    ) {
        return Recipe.builder()
                .userId(userId)
                .name(name)
                .imageUrl(imageUrl)
                .cookingTime(cookingTime)
                .calories(calories)
                .oneLineIntroduction(oneLineIntroduction)
                .recipeIngredients(recipeIngredients)
                .introduction(introduction)
                .nutrition(nutrition)
                .build();
    }

    public static Recipe of(
            Long id, Long userId, String name, String imageUrl, short cookingTime, short calories, String oneLineIntroduction,
            List<RecipeIngredient> recipeIngredients, String introduction, Nutrition nutrition,
            LocalDateTime createdAt, LocalDateTime modifiedAt
    ) {
        return Recipe.builder()
                .id(id)
                .userId(userId)
                .name(name)
                .imageUrl(imageUrl)
                .cookingTime(cookingTime)
                .calories(calories)
                .oneLineIntroduction(oneLineIntroduction)
                .recipeIngredients(recipeIngredients)
                .introduction(introduction)
                .nutrition(nutrition)
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .build();
    }

    public void update(
            String oneLineIntroduction, List<RecipeIngredient> recipeIngredients,
            String introduction, Nutrition nutrition
    ) {
        this.oneLineIntroduction = oneLineIntroduction;
        this.recipeIngredients = recipeIngredients;
        this.introduction = introduction;
        this.nutrition = nutrition;
    }
}
