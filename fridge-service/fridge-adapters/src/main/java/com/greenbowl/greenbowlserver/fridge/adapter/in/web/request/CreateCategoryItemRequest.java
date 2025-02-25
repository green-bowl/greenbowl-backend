package com.greenbowl.greenbowlserver.fridge.adapter.in.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.greenbowl.greenbowlserver.fridge.adapter.in.web.ApiConstant.*;

@Getter
public class CreateCategoryItemRequest {
    @NotNull
    @Min(1)
    @Max(8)
    @ApiModelProperty(value = CATEGORY_SEQUENCE, example = CATEGORY_SEQUENCE_EXAMPLE)
    private int sequence;
    @NotNull
    @Size(min = 1, max = 255)
    @ApiModelProperty(value = CATEGORY_DETAIL, example = CATEGORY_DETAIL_EXAMPLE)
    private String categoryDetail;
}
