package com.greenbowl.greenbowlserver.recipe.port.in.command;


import lombok.Getter;

@Getter
public class IngredientCommand {
    private String name;
    private short weight;

    private IngredientCommand(String name, short weight) {
        this.name = name;
        this.weight = weight;
    }

    public static IngredientCommand of(String name, short weight) {
        return new IngredientCommand(name, weight);
    }
}
