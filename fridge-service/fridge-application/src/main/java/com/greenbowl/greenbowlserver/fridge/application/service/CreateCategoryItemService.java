package com.greenbowl.greenbowlserver.fridge.application.service;

import com.greenbowl.greenbowlserver.fridge.application.port.in.command.CreateCategoryItemCommand;
import com.greenbowl.greenbowlserver.fridge.application.port.in.mapper.CategoryItemCommandToDomainMapper;
import com.greenbowl.greenbowlserver.fridge.application.port.in.usecase.CreateCategoryItemUseCase;
import com.greenbowl.greenbowlserver.fridge.application.port.out.CreateCategoryItemPort;
import com.greenbowl.greenbowlserver.fridge.domain.CategoryItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@RequiredArgsConstructor
@Transactional(isolation = SERIALIZABLE)
public class CreateCategoryItemService implements CreateCategoryItemUseCase {
    private final CreateCategoryItemPort createCategoryItemPort;

    @Override
    public CategoryItem createCategoryItem(Long userId, CreateCategoryItemCommand createCategoryItemCommand) {
        CategoryItem categoryItem = CategoryItemCommandToDomainMapper.mapToDomainEntity(userId, createCategoryItemCommand);

        return createCategoryItemPort.saveCategoryItem(categoryItem);
    }
}
