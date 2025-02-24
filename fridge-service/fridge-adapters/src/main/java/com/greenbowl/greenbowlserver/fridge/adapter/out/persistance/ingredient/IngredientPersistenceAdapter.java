package com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.ingredient;

import com.greenbowl.greenbowlserver.fridge.adapter.out.mapper.FridgeJpaEntityToDomainMapper;
import com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.categoryitem.CategoryItemJpaEntity;
import com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.categoryitem.CategoryItemJpaRepository;
import com.greenbowl.greenbowlserver.fridge.application.port.in.IngredientResult;
import com.greenbowl.greenbowlserver.fridge.application.port.in.command.DeleteIngredientCommand;
import com.greenbowl.greenbowlserver.fridge.application.port.out.*;
import com.greenbowl.greenbowlserver.fridge.domain.CategoryItem;
import com.greenbowl.greenbowlserver.fridge.domain.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class IngredientPersistenceAdapter implements
        CreateIngredientPort, DeleteIngredientPort, GetIngredientPort, UpdateIngredientPort {
    private final IngredientJpaRepository ingredientJpaRepository;
    private final CategoryItemJpaRepository categoryItemJpaRepository;

    @Override
    public List<Ingredient> saveIngredient(List<Ingredient> ingredients) {
        List<IngredientJpaEntity> entities = ingredients.stream()
                .map(ingredient -> {

                    CategoryItemJpaEntity categoryItemJpaEntity = categoryItemJpaRepository.findById(ingredient.getCategoryId())
                            .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없음: " + ingredient.getCategoryId()));

                    return IngredientJpaEntity.from(ingredient, categoryItemJpaEntity);
                })
                .collect(Collectors.toList());

        List<IngredientJpaEntity> savedEntities = ingredientJpaRepository.saveAll(entities);

        return savedEntities.stream()
                .map(FridgeJpaEntityToDomainMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Ingredient> getIngredientsByUserId(Long userId) {
        List<CategoryItemJpaEntity> categoryItemEntities = categoryItemJpaRepository
                .findAllByUserIdAndDeleteYnFalse(userId);

        List<IngredientJpaEntity> ingredientEntities = new ArrayList<>();

        for (CategoryItemJpaEntity categoryItemEntity : categoryItemEntities) {
            List<IngredientJpaEntity> ingredientsForCategory =
                    ingredientJpaRepository.findAllByCategoryItemJpaEntity_IdAndDeleteYnFalse(categoryItemEntity.getId());
            ingredientEntities.addAll(ingredientsForCategory);
        }

        return ingredientEntities.stream()
                .map(FridgeJpaEntityToDomainMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<IngredientResult> updateIngredients(Long userId,
                                                    List<Ingredient> ingredients) {

        List<CategoryItemJpaEntity> categoryItemJpaEntities = categoryItemJpaRepository
                .findAllByUserIdAndDeleteYnFalse(userId);

        Map<Long, CategoryItemJpaEntity> categoryItemMap = categoryItemJpaEntities.stream()
                .collect(Collectors.toMap(CategoryItemJpaEntity::getId, entity -> entity));

        List<IngredientJpaEntity> updateEntities = ingredients.stream()
                .map(ingredient -> {
                    IngredientJpaEntity existingEntity = ingredientJpaRepository.findById(ingredient.getId())
                            .orElseThrow(() -> new IllegalArgumentException("재료를 찾을 수 없음: " + ingredient.getId()));

                    CategoryItemJpaEntity categoryItemJpaEntity = categoryItemMap.get(ingredient.getCategoryId());

                    if (categoryItemJpaEntity == null) {
                        throw new IllegalArgumentException("해당 사용자에 대한 카테고리를 찾을 수 없음: " + ingredient.getCategoryId());
                    }

                    existingEntity.update(ingredient, categoryItemJpaEntity);

                    return existingEntity;
                })
                .collect(Collectors.toList());

        return updateEntities.stream()
                .map(entity -> {
                    Ingredient ingredientDomain = FridgeJpaEntityToDomainMapper.mapToDomainEntity(entity);
                    CategoryItem categoryItem = FridgeJpaEntityToDomainMapper.mapToDomainEntity(entity.getCategoryItemJpaEntity());
                    return IngredientResult.from(ingredientDomain, categoryItem);
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteIngredient(List<DeleteIngredientCommand> deleteIngredientCommands) {
        deleteIngredientCommands.forEach(command -> {
            IngredientJpaEntity entity = ingredientJpaRepository.findById(command.getId())
                    .orElseThrow(() -> new IllegalArgumentException("재료를 찾을 수 없음: " + command.getId()));
            entity.deleteIngredient();
        });
    }
}
