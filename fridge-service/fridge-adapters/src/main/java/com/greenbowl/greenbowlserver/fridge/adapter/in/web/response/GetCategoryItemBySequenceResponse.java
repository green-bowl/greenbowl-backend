package com.greenbowl.greenbowlserver.fridge.adapter.in.web.response;

import com.greenbowl.greenbowlserver.fridge.domain.CategoryItem;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.WrapperAccessor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class GetCategoryItemBySequenceResponse {
    private Long categoryItemId;
    private String categoryDetail;

    public static GetCategoryItemBySequenceResponse from (CategoryItem categoryItem) {
        return GetCategoryItemBySequenceResponse.builder()
                .categoryItemId(categoryItem.getId())
                .categoryDetail(WrapperAccessor.getCategoryDetail(categoryItem.getCategoryDetail()))
                .build();
    }
}
