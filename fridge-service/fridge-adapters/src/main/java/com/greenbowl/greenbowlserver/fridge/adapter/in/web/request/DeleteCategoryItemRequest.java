package com.greenbowl.greenbowlserver.fridge.adapter.in.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import static com.greenbowl.greenbowlserver.fridge.adapter.in.web.ApiConstant.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class DeleteCategoryItemRequest {
    @NotNull
    @ApiModelProperty(value = CATEGORY_ID, example = CATEGORY_ID_EXAMPLE)
    private Long id;
}
