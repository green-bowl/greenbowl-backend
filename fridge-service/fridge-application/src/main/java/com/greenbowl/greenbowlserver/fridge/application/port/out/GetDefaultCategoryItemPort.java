package com.greenbowl.greenbowlserver.fridge.application.port.out;

import com.greenbowl.greenbowlserver.fridge.domain.DefaultCategoryItem;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.Category;

import java.util.List;

public interface GetDefaultCategoryItemPort {
    List<DefaultCategoryItem> getCategoryItemBySequence(Category category);
    List<DefaultCategoryItem> getAllCategoryItems();
}
