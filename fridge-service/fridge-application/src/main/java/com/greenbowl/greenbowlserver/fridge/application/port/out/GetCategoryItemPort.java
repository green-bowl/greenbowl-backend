package com.greenbowl.greenbowlserver.fridge.application.port.out;

import com.greenbowl.greenbowlserver.fridge.domain.CategoryItem;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.Category;

import java.util.List;

public interface GetCategoryItemPort {
    CategoryItem getCategoryItem(Long userId);

    List<CategoryItem> getCategoryItemsByUserId(Long userId);

    CategoryItem getCategoryItemById(Long categoryId);

    List<CategoryItem> getCategoryItemsByUserIdAndSequence(Long userId, Category category);
}
