package com.greenbowl.greenbowlserver.recipe.adapter.in.web.response;

import com.greenbowl.greenbowlserver.common.utility.FormatValidator;
import com.greenbowl.greenbowlserver.recipe.domain.Nutrition;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NutritionResponse {
    private short carbohydrate;
    private short protein;
    private short fat;
    private short sodium;
    private short sugar;

    @Builder
    private NutritionResponse(short carbohydrate, short protein, short fat, short sodium, short sugar) {
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.sugar = sugar;
    }

    public static NutritionResponse from(Nutrition nutrition) {
        if (FormatValidator.hasValue(nutrition)) {
            return NutritionResponse.builder()
                    .carbohydrate(nutrition.getCarbohydrate())
                    .protein(nutrition.getProtein())
                    .fat(nutrition.getFat())
                    .sodium(nutrition.getSodium())
                    .sugar(nutrition.getSugar())
                    .build();
        }

        return null;
    }
}
