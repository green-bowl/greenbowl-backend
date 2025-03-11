package com.greenbowl.greenbowlserver.fridge.application.service;

import com.greenbowl.greenbowlserver.fridge.application.port.in.command.DeleteCategoryItemCommand;
import com.greenbowl.greenbowlserver.fridge.application.port.in.usecase.DeleteCategoryItemUseCase;
import com.greenbowl.greenbowlserver.fridge.application.port.out.DeleteCategoryItemPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteCategoryItemService implements DeleteCategoryItemUseCase {
    private final DeleteCategoryItemPort deleteCategoryItemPort;

    @Override
    public void deleteCategoryItem(Long userId, DeleteCategoryItemCommand deleteCategoryItemCommand) {
        deleteCategoryItemPort.deleteCategoryItem(userId, deleteCategoryItemCommand);
    }
}
