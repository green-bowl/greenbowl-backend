package com.greenbowl.greenbowlserver.fridge.application.port.in.usecase;

import com.greenbowl.greenbowlserver.fridge.application.port.in.command.CreateCategoryItemCommand;
import com.greenbowl.greenbowlserver.fridge.domain.CategoryItem;

public interface CreateCategoryItemUseCase {

    CategoryItem createCategoryItem(Long userId, CreateCategoryItemCommand createCategoryItemCommand);
}
