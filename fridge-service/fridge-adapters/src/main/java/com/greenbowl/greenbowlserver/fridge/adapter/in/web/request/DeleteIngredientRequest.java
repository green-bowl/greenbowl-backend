package com.greenbowl.greenbowlserver.fridge.adapter.in.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

import static com.greenbowl.greenbowlserver.fridge.adapter.in.web.ApiConstant.*;

@Getter
public class DeleteIngredientRequest {
    @NotNull
    @ApiModelProperty(value = INGREDIENT_ID, example = INGREDIENT_ID_EXAMPLE)
    private Long id;
}
