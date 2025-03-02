package com.greenbowl.greenbowlserver.fridge.application.port.out;

import com.greenbowl.greenbowlserver.fridge.domain.CategoryItem;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.Category;

import java.util.List;

public interface GetCategoryItemPort {
    List<CategoryItem> getCategoryItemsByIds(List<Long> ids);
    List<CategoryItem> getCategoryItemsByUserId(Long userId);
    List<CategoryItem> getCategoryItemsByUserIdAndSequence(Long userId, Category category);
}
