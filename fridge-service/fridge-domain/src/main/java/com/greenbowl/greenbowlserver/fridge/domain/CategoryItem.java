package com.greenbowl.greenbowlserver.fridge.domain;

import com.greenbowl.greenbowlserver.fridge.domain.wrapper.Category;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.CategoryDetail;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryItem {
    private final Long id;
    private final Long userId;
    private final CategoryDetail categoryDetail;
    private final Category category;

    @Builder
    public CategoryItem(Long id,Long userId, CategoryDetail categoryDetail, Category category) {
        this.id = id;
        this.userId = userId;
        this.categoryDetail = categoryDetail;
        this.category = category;
    }
}
