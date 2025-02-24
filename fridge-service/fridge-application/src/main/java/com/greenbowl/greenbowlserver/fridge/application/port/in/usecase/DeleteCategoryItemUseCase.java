package com.greenbowl.greenbowlserver.fridge.application.port.in.usecase;

import com.greenbowl.greenbowlserver.fridge.application.port.in.command.DeleteCategoryItemCommand;

public interface DeleteCategoryItemUseCase {
    void deleteCategoryItem(Long userId, DeleteCategoryItemCommand deleteCategoryItemCommand);
}
