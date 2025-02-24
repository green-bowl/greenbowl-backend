package com.greenbowl.greenbowlserver.fridge.adapter.in.web.response;

import com.greenbowl.greenbowlserver.fridge.domain.CategoryItem;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.WrapperAccessor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateCategoryItemResponse {
    private Long id;
    private int sequence;
    private String categoryDetail;

    public static CreateCategoryItemResponse from(CategoryItem categoryItem) {
        return CreateCategoryItemResponse.builder()
                .id(categoryItem.getId())
                .sequence(categoryItem.getCategory().getSequence())
                .categoryDetail(WrapperAccessor.getCategoryDetail(categoryItem.getCategoryDetail()))
                .build();
    }
}
