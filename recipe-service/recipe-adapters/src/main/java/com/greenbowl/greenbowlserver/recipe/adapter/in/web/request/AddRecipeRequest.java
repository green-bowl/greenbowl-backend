package com.greenbowl.greenbowlserver.recipe.adapter.in.web.request;

import lombok.Getter;

@Getter
public class AddRecipeRequest {
    private String name;
    private String imageUrl;
    private String cookingTime;
    private String calories;
}
