package com.greenbowl.greenbowlserver.fridge.application.port.out;

import com.greenbowl.greenbowlserver.fridge.domain.CategoryItem;

import java.util.List;

public interface GetCategoryItemPort {
    CategoryItem getCategoryItem(Long userId);

    List<CategoryItem> getCategoryItemsByUserId(Long userId);

    CategoryItem getCategoryItemById(Long categoryId);
}
