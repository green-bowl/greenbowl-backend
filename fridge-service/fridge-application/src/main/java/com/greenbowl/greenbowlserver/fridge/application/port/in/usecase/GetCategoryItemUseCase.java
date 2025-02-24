package com.greenbowl.greenbowlserver.fridge.application.port.in.usecase;

import com.greenbowl.greenbowlserver.fridge.domain.CategoryItem;

import java.util.List;

public interface GetCategoryItemUseCase {
    List<CategoryItem> getCategoryItems(Long userId);
}
