package com.greenbowl.greenbowlserver.recipe.domain;

import lombok.Getter;

@Getter
public class RecipIngredient {
    private String name;
    private short weight;

    private RecipIngredient(String name, short weight) {
        this.name = name;
        this.weight = weight;
    }

    public static RecipIngredient of(String name, short weight) {
        return new RecipIngredient(name, weight);
    }
}
