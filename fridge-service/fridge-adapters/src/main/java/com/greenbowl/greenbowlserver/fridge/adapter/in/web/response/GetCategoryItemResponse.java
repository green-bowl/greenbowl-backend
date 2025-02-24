package com.greenbowl.greenbowlserver.fridge.adapter.in.web.response;

import com.greenbowl.greenbowlserver.fridge.domain.CategoryItem;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.WrapperAccessor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class GetCategoryItemResponse {
    private Long id;
    private int sequence;
    private String categoryDetail;

    public static GetCategoryItemResponse from(CategoryItem categoryItem) {
        return GetCategoryItemResponse.builder()
                .id(categoryItem.getId())
                .sequence(categoryItem.getCategory().getSequence())
                .categoryDetail(WrapperAccessor.getCategoryDetail(categoryItem.getCategoryDetail()))
                .build();
    }
}
