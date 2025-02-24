package com.greenbowl.greenbowlserver.fridge.application.port.in.mapper;

import com.greenbowl.greenbowlserver.fridge.application.port.in.command.CreateCategoryItemCommand;
import com.greenbowl.greenbowlserver.fridge.application.port.in.command.GetCategoryItemCommand;
import com.greenbowl.greenbowlserver.fridge.domain.CategoryItem;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.Category;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.CategoryDetail;

public class CategoryItemCommandToDomainMapper {
    public static CategoryItem mapToDomainEntity(Long userId, CreateCategoryItemCommand createCategoryItemCommand) {
        return CategoryItem.builder()
                .userId(userId)
                .categoryDetail(CategoryDetail.of(createCategoryItemCommand.getCategoryDetail()))
                .category(Category.from(createCategoryItemCommand.getSequence()))
                .build();
    }

    public static CategoryItem mapToDomainEntity(GetCategoryItemCommand command) {
        return CategoryItem.builder()
                .userId(command.getUserId())
                .build();
    }
}
