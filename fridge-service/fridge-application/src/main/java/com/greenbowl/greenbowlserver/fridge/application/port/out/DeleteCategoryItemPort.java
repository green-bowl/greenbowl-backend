package com.greenbowl.greenbowlserver.fridge.application.port.out;

import com.greenbowl.greenbowlserver.fridge.application.port.in.command.DeleteCategoryItemCommand;

public interface DeleteCategoryItemPort {
    void deleteCategoryItem(Long userId, DeleteCategoryItemCommand deleteCategoryItemCommand);
}
