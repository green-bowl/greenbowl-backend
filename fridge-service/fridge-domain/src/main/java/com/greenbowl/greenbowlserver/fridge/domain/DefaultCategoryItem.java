package com.greenbowl.greenbowlserver.fridge.domain;

import com.greenbowl.greenbowlserver.fridge.domain.wrapper.Category;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.CategoryDetail;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DefaultCategoryItem {
    private final Long id;
    private final CategoryDetail categoryDetail;
    private final Category category;

    @Builder
    public DefaultCategoryItem(Long id, CategoryDetail categoryDetail, Category category) {
        this.id = id;
        this.categoryDetail = categoryDetail;
        this.category = category;
    }
}
