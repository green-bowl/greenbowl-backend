package com.greenbowl.greenbowlserver.recipe.adapter.in.web.request;

import lombok.Getter;

import java.util.List;

@Getter
public class AddDetailedRecipeRequest {
    private String name;
    private String imageUrl;
    private String cookingTime;
    private String calories;
    private String oneLineIntroduction;
    private String introduction;
    private List<UsedIngredientRequest> ingredients;
    private NutritionRequest nutrition;
}
