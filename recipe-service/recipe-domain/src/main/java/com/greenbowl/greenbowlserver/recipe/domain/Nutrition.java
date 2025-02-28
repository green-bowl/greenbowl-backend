package com.greenbowl.greenbowlserver.recipe.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Nutrition {
    private short carbohydrate;
    private short protein;
    private short fat;
    private short sodium;
    private short sugar;

    @Builder
    private Nutrition(short carbohydrate, short protein, short fat, short sodium, short sugar) {
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.sugar = sugar;
    }

    public static Nutrition of(short carbohydrate, short protein, short fat, short sodium, short sugar) {
        return Nutrition.builder()
                .carbohydrate(carbohydrate)
                .protein(protein)
                .fat(fat)
                .sodium(sodium)
                .sugar(sugar)
                .build();
    }
}
