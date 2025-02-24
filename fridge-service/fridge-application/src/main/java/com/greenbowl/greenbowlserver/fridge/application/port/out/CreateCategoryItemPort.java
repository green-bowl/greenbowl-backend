package com.greenbowl.greenbowlserver.fridge.application.port.out;

import com.greenbowl.greenbowlserver.fridge.domain.CategoryItem;

public interface CreateCategoryItemPort {
    CategoryItem saveCategoryItem(CategoryItem categoryItem);
}
