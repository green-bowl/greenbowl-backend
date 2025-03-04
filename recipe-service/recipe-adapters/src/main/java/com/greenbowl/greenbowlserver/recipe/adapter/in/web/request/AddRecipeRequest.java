package com.greenbowl.greenbowlserver.recipe.adapter.in.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import static com.greenbowl.greenbowlserver.common.utility.ApiConstant.RECIPE_NAME_EXAMPLE;
import static com.greenbowl.greenbowlserver.common.utility.ApiConstant.RECIPE_NAME_VALUE;

@Getter
public class AddRecipeRequest {
    private static final String IMAGE_URL_VALUE = "이미지 주소";
    private static final String IMAGE_URL_EXAMPLE
            = "https://ddipddipddip.s3.ap-northeast-2.amazonaws.com/images/default_food_image.jpg";

    private static final String COOKING_TIME_VALUE = "조리 시간 (분)";
    private static final String COOKING_TIME_VALUE_EXAMPLE = "20";

    private static final String CALORIES_VALUE = "열량 (kcal)";
    private static final String CALORIES_EXAMPLE = "250";

    @ApiModelProperty(value = RECIPE_NAME_VALUE, example = RECIPE_NAME_EXAMPLE)
    private String name;

    @ApiModelProperty(value = IMAGE_URL_VALUE, example = IMAGE_URL_EXAMPLE)
    private String imageUrl;

    @ApiModelProperty(value = COOKING_TIME_VALUE, example = COOKING_TIME_VALUE_EXAMPLE)
    private String cookingTime;

    @ApiModelProperty(value = CALORIES_VALUE, example = CALORIES_EXAMPLE)
    private String calories;
}
