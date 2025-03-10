package com.greenbowl.greenbowlserver.fridge.application.service;

import com.greenbowl.greenbowlserver.common.application.UseCase;
import com.greenbowl.greenbowlserver.fridge.application.port.in.usecase.GetCategoryItemUseCase;
import com.greenbowl.greenbowlserver.fridge.application.port.out.GetCategoryItemPort;
import com.greenbowl.greenbowlserver.fridge.domain.CategoryItem;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.Category;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetCategoryItemService implements GetCategoryItemUseCase {
    private final GetCategoryItemPort getCategoryItemPort;

    @Override
    public List<CategoryItem> getCategoryItems(Long userId) {
        return getCategoryItemPort.getCategoryItemsByUserId(userId);
    }

    @Override
    public List<CategoryItem> getCategoryItemBySequence(Long userId, int sequence) {
        return getCategoryItemPort.getCategoryItemsByUserIdAndSequence(userId, Category.from(sequence));
    }
}
