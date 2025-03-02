package com.greenbowl.greenbowlserver.recipe.adapter.in.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class NutritionRequest {
    private static final String CARBOHYDRATE_NAME_VALUE = "탄수화물";
    private static final String CARBOHYDRATE_CALORIES_EXAMPLE = "15";

    private static final String PROTEIN_NAME_VALUE = "단백질";
    private static final String PROTEIN_CALORIES_EXAMPLE = "10";

    private static final String FAT_NAME_VALUE = "지방";
    private static final String FAT_CALORIES_EXAMPLE = "5";

    private static final String SODIUM_NAME_VALUE = "나트륨";
    private static final String SODIUM_CALORIES_EXAMPLE = "300";

    private static final String SUGAR_NAME_VALUE = "당류";
    private static final String SUGAR_CALORIES_EXAMPLE = "2";

    @ApiModelProperty(value = CARBOHYDRATE_NAME_VALUE, example = CARBOHYDRATE_CALORIES_EXAMPLE)
    private String carbohydrate;

    @ApiModelProperty(value = PROTEIN_NAME_VALUE, example = PROTEIN_CALORIES_EXAMPLE)
    private String protein;

    @ApiModelProperty(value = FAT_NAME_VALUE, example = FAT_CALORIES_EXAMPLE)
    private String fat;

    @ApiModelProperty(value = SODIUM_NAME_VALUE, example = SODIUM_CALORIES_EXAMPLE)
    private String sodium;

    @ApiModelProperty(value = SUGAR_NAME_VALUE, example = SUGAR_CALORIES_EXAMPLE)
    private String sugar;
}
