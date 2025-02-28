package com.greenbowl.greenbowlserver.recipe.domain;

import lombok.Getter;

@Getter
public class RecipeIngredient {
    private String name;
    private short weight;

    private RecipeIngredient(String name, short weight) {
        this.name = name;
        this.weight = weight;
    }

    public static RecipeIngredient of(String name, short weight) {
        return new RecipeIngredient(name, weight);
    }
}
