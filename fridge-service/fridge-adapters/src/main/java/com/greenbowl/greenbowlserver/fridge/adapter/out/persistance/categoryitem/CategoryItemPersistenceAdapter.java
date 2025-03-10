package com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.categoryitem;

import com.greenbowl.greenbowlserver.common.adapter.out.PersistenceAdapter;
import com.greenbowl.greenbowlserver.fridge.adapter.out.mapper.FridgeJpaEntityToDomainMapper;
import com.greenbowl.greenbowlserver.fridge.application.port.in.command.DeleteCategoryItemCommand;
import com.greenbowl.greenbowlserver.fridge.application.port.out.CreateCategoryItemPort;
import com.greenbowl.greenbowlserver.fridge.application.port.out.DeleteCategoryItemPort;
import com.greenbowl.greenbowlserver.fridge.application.port.out.GetCategoryItemPort;
import com.greenbowl.greenbowlserver.fridge.domain.CategoryItem;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.Category;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class CategoryItemPersistenceAdapter implements
        CreateCategoryItemPort, DeleteCategoryItemPort, GetCategoryItemPort {
    private final CategoryItemJpaRepository categoryItemJpaRepository;

    @Override
    public CategoryItem saveCategoryItem(CategoryItem categoryItem) {
        CategoryItemJpaEntity categoryItemJpaEntity = CategoryItemJpaEntity.from(categoryItem);

        if (categoryItemJpaRepository.existsByCategoryDetailAndDeleteYnFalseAndCategory(
                categoryItem.getCategoryDetail(),
                categoryItem.getCategory()
        )){
            throw new EntityExistsException("이미 존재하는 카테고리 입니다.");
        }
        categoryItemJpaRepository.save(categoryItemJpaEntity);

        return FridgeJpaEntityToDomainMapper.mapToDomainEntity(categoryItemJpaEntity);
    }

    @Override
    public List<CategoryItem> getCategoryItemsByIds(List<Long> ids) {
        List<CategoryItemJpaEntity> categoryItemJpaEntities
                = categoryItemJpaRepository.findByIdInAndDeleteYnFalse(ids);
        return categoryItemJpaEntities.stream()
                .map(FridgeJpaEntityToDomainMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryItem> getCategoryItemsByUserId(Long userId) {
        List<CategoryItemJpaEntity> categoryItemJpaEntity = categoryItemJpaRepository
                .findAllByUserIdAndDeleteYnFalse(userId);
        return categoryItemJpaEntity.stream()
                .map(FridgeJpaEntityToDomainMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryItem> getCategoryItemsByUserIdAndSequence(Long userId, Category category) {
        List<CategoryItemJpaEntity> result = new ArrayList<>();

        List<CategoryItemJpaEntity> defaultCategoryItem = categoryItemJpaRepository
                .findAllByUserIdNullAndDeleteYnFalseAndIsDefaultTrue();

        List<CategoryItemJpaEntity> categoryItemJpaEntities
                = categoryItemJpaRepository.findAllByUserIdAndCategoryAndDeleteYnFalse(userId, category);
        result.addAll(defaultCategoryItem);
        result.addAll(categoryItemJpaEntities);

        return result.stream()
                .map(FridgeJpaEntityToDomainMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategoryItem(Long userId, DeleteCategoryItemCommand deleteCategoryItemCommand) {
        CategoryItemJpaEntity categoryItemJpaEntity
                = categoryItemJpaRepository.findByUserIdAndIdAndDeleteYnFalse(userId, deleteCategoryItemCommand.getId())
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 항목입니다."));

        if (categoryItemJpaEntity.isDefault()){
            throw new IllegalArgumentException("기본 항목은 삭제할 수 없습니다.");
        }
        categoryItemJpaEntity.deleteCategoryItem();
    }

}
