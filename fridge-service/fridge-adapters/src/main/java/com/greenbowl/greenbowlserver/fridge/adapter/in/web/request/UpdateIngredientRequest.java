package com.greenbowl.greenbowlserver.fridge.adapter.in.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static com.greenbowl.greenbowlserver.fridge.adapter.in.web.ApiConstant.*;

@Getter
public class UpdateIngredientRequest {
    @NotNull
    @ApiModelProperty(value = INGREDIENT_ID, example = INGREDIENT_ID_EXAMPLE)
    private Long id;
    @NotNull
    @Size(min = 1, max = 99)
    @ApiModelProperty(value = INGREDIENT_QUANTITY, example = INGREDIENT_QUANTITY_EXAMPLE)
    private int quantity;
    @NotNull
    @ApiModelProperty(value = INGREDIENT_STORAGE_METHOD, example = INGREDIENT_STORAGE_METHOD_EXAMPLE)
    private String storageMethod;
    @NotNull
    @ApiModelProperty(value = INGREDIENT_EXPIRATION_DATE, example = INGREDIENT_EXPIRATION_DATE_EXAMPLE)
    private LocalDate expirationDate;
    @NotNull
    @ApiModelProperty(value = CATEGORY_ID, example = CATEGORY_ID_EXAMPLE)
    private Long categoryId;
}
