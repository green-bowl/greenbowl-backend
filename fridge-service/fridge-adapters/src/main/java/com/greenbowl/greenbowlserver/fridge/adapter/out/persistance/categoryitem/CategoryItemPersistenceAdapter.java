package com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.categoryitem;

import com.greenbowl.greenbowlserver.fridge.adapter.out.mapper.FridgeJpaEntityToDomainMapper;
import com.greenbowl.greenbowlserver.fridge.application.port.in.command.DeleteCategoryItemCommand;
import com.greenbowl.greenbowlserver.fridge.application.port.out.CreateCategoryItemPort;
import com.greenbowl.greenbowlserver.fridge.application.port.out.DeleteCategoryItemPort;
import com.greenbowl.greenbowlserver.fridge.application.port.out.GetCategoryItemPort;
import com.greenbowl.greenbowlserver.fridge.domain.CategoryItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryItemPersistenceAdapter implements
        CreateCategoryItemPort, DeleteCategoryItemPort, GetCategoryItemPort {
    private final CategoryItemJpaRepository categoryItemJpaRepository;

    @Override
    public CategoryItem saveCategoryItem(CategoryItem categoryItem) {
        CategoryItemJpaEntity categoryItemJpaEntity = CategoryItemJpaEntity.from(categoryItem);
        categoryItemJpaRepository.save(categoryItemJpaEntity);

        return FridgeJpaEntityToDomainMapper.mapToDomainEntity(categoryItemJpaEntity);
    }

    @Override
    public CategoryItem getCategoryItem(Long userId) {
        CategoryItemJpaEntity categoryItemJpaEntity = categoryItemJpaRepository.findByUserIdAndDeleteYnFalse(userId);
        return FridgeJpaEntityToDomainMapper.mapToDomainEntity(categoryItemJpaEntity);
    }

    @Override
    public List<CategoryItem> getCategoryItemsByUserId(Long userId) {
        List<CategoryItemJpaEntity> categoryItemJpaEntity = categoryItemJpaRepository.findAllByUserIdAndDeleteYnFalse(userId);
        return categoryItemJpaEntity.stream()
                .map(FridgeJpaEntityToDomainMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryItem getCategoryItemById(Long categoryId) {
        CategoryItemJpaEntity categoryItemJpaEntity = categoryItemJpaRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없음: " + categoryId));
        return FridgeJpaEntityToDomainMapper.mapToDomainEntity(categoryItemJpaEntity);
    }

    @Override
    public void deleteCategoryItem(Long userId, DeleteCategoryItemCommand deleteCategoryItemCommand) {
        CategoryItemJpaEntity categoryItemJpaEntity
                = categoryItemJpaRepository.findByUserIdAndIdAndDeleteYnFalse(userId, deleteCategoryItemCommand.getId());
        categoryItemJpaEntity.deleteCategoryItem();
    }

}
