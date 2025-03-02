package com.greenbowl.greenbowlserver.recipe.adapter.in.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class UsedIngredientRequest {
    private static final String INGREDIENT_NAME_VALUE = "재료명";
    private static final String INGREDIENT_NAME_EXAMPLE = "된장";

    private static final String INGREDIENT_WEIGHT_VALUE = "재료 무게";
    private static final String INGREDIENT_WEIGHT_EXAMPLE = "50";

    @ApiModelProperty(value = INGREDIENT_NAME_VALUE, example = INGREDIENT_NAME_EXAMPLE)
    private String name;

    @ApiModelProperty(value = INGREDIENT_WEIGHT_VALUE, example = INGREDIENT_WEIGHT_EXAMPLE)
    private String weight;
}
