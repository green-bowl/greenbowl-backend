package com.greenbowl.greenbowlserver.recipe.adapter.in.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.List;

import static com.greenbowl.greenbowlserver.common.utility.ApiConstant.RECIPE_NAME_EXAMPLE;
import static com.greenbowl.greenbowlserver.common.utility.ApiConstant.RECIPE_NAME_VALUE;

@Getter
public class AddDetailedRecipeRequest {
    private static final String IMAGE_URL_VALUE = "이미지 주소";
    private static final String IMAGE_URL_EXAMPLE
            = "https://ddipddipddip.s3.ap-northeast-2.amazonaws.com/images/default_food_image.jpg";

    private static final String COOKING_TIME_VALUE = "조리 시간 (분)";
    private static final String COOKING_TIME_VALUE_EXAMPLE = "20";

    private static final String CALORIES_VALUE = "열량 (kcal)";
    private static final String CALORIES_EXAMPLE = "250";

    private static final String ONE_LINE_INTRODUCTION_VALUE = "한 줄 소개";
    private static final String ONE_LINE_INTRODUCTION_VALUE_EXAMPLE = "단백질이 풍부한 돼지고기와 된장을 넣어 만든 한국 전통 찌개";

    private static final String INTRODUCTION_VALUE = "레시피 설명";
    private static final String INTRODUCTION_EXAMPLE = "\n\n물론입니다! 주어진 재료와 조건에 맞는 된장찌개 레시피를 추천해 드리겠습니다.\n\n### 된장찌개 레시피\n\n#### 재료:\n- 돼지고기: 50g\n- 된장: 20g\n- 후추: 1g\n- 소금: 3g\n- 마늘: 5g\n- 양파: 30g\n- 피망: 10g\n\n#### 조리 시간:\n- 20분\n\n#### 칼로리:\n- 250kcal\n\n#### 조리 방법:\n1. **준비 단계:**\n   - 돼지고기는 깨끗이 씻어 물기를 제거한 후 적당한 크기로 썰어줍니다.\n   - 양파와 마늘은 다진 후 준비해 줍니다.\n   - 피망은 깨끗이 씻어 적당한 크기로 썰어줍니다.\n\n2. **돼지고기 볶기:**\n   - 냄비에 약간의 기름을 두르고 중간 불에 예열합니다.\n   - 썬 돼지고기를 넣고 갈색이 될 때까지 볶아줍니다.\n\n3. **된장 볶기:**\n   - 볶은 돼지고기에 된장을 넣고 잘 섞어줍니다.\n   - 다진 마늘과 양파를 넣고 함께 볶아줍니다.\n\n4. **물 붓기:**\n   - 냄비에 물을 적당히 붓고 끓여줍니다.\n   - 썰은 피망을 넣고 함께 끓여줍니다.\n\n5. **간 맞추기:**\n   - 소금과 후추로 간을 맞춰줍니다.\n   - 끓어오르면 중간 불에서 10분 정도 더 끓여줍니다.\n\n6. **완성:**\n   - 찌개가 충분히 끓어오르면 불을 끄고 그릇에 담아냅니다.\n   - 밥과 함께 맛있게 즐기세요!\n\n이 레시피를 따라 만들면 맛있는 된장찌개를 즐길 수 있을 거예요. 맛있게 드세요!\n";

    private static final String USED_INGREDIENT_REQUEST_FORM = "사용된 재료 요청 양식";
    private static final String NUTRITION_REQUEST_FORM = "영양 성분 요청 양식";

    @ApiModelProperty(value = RECIPE_NAME_VALUE, example = RECIPE_NAME_EXAMPLE)
    private String name;

    @ApiModelProperty(value = IMAGE_URL_VALUE, example = IMAGE_URL_EXAMPLE)
    private String imageUrl;

    @ApiModelProperty(value = COOKING_TIME_VALUE, example = COOKING_TIME_VALUE_EXAMPLE)
    private String cookingTime;

    @ApiModelProperty(value = CALORIES_VALUE, example = CALORIES_EXAMPLE)
    private String calories;

    @ApiModelProperty(value = ONE_LINE_INTRODUCTION_VALUE, example = ONE_LINE_INTRODUCTION_VALUE_EXAMPLE)
    private String oneLineIntroduction;

    @ApiModelProperty(value = INTRODUCTION_VALUE, example = INTRODUCTION_EXAMPLE)
    private String introduction;

    @ApiModelProperty(value = USED_INGREDIENT_REQUEST_FORM)
    private List<UsedIngredientRequest> ingredients;

    @ApiModelProperty(value = NUTRITION_REQUEST_FORM)
    private NutritionRequest nutrition;
}
