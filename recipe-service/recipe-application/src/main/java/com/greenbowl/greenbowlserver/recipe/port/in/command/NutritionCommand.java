package com.greenbowl.greenbowlserver.recipe.port.in.command;


import lombok.Builder;
import lombok.Getter;

@Getter
public class NutritionCommand {
    private short carbohydrate;
    private short protein;
    private short fat;
    private short sodium;
    private short sugar;

    @Builder
    private NutritionCommand(short carbohydrate, short protein, short fat, short sodium, short sugar) {
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.sugar = sugar;
    }

    public static NutritionCommand of(short carbohydrate, short protein, short fat, short sodium, short sugar) {
        return NutritionCommand.builder()
                .carbohydrate(carbohydrate)
                .protein(protein)
                .fat(fat)
                .sodium(sodium)
                .sugar(sugar)
                .build();
    }
}
