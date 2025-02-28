package com.greenbowl.greenbowlserver.recipe.adapter.out.persistence.recipe;

import com.greenbowl.greenbowlserver.recipe.domain.Nutrition;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class EmbeddableNutrition {
    private short carbohydrate;
    private short protein;
    private short fat;
    private short sodium;
    private short sugar;

    @Builder
    private EmbeddableNutrition(short carbohydrate, short protein, short fat, short sodium, short sugar) {
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.sugar = sugar;
    }

    public static EmbeddableNutrition from(Nutrition nutrition) {
        return EmbeddableNutrition.builder()
                .carbohydrate(nutrition.getCarbohydrate())
                .protein(nutrition.getProtein())
                .fat(nutrition.getFat())
                .sodium(nutrition.getSodium())
                .sugar(nutrition.getSugar())
                .build();
    }
}
