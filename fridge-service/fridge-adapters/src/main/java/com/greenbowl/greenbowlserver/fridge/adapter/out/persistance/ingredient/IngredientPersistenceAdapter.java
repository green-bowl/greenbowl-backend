package com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.ingredient;

import com.greenbowl.greenbowlserver.common.adapter.out.PersistenceAdapter;
import com.greenbowl.greenbowlserver.fridge.adapter.out.mapper.FridgeJpaEntityToDomainMapper;
import com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.categoryitem.CategoryItemJpaEntity;
import com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.categoryitem.CategoryItemJpaRepository;
import com.greenbowl.greenbowlserver.fridge.application.port.in.IngredientResult;
import com.greenbowl.greenbowlserver.fridge.application.port.in.command.DeleteIngredientCommand;
import com.greenbowl.greenbowlserver.fridge.application.port.out.CreateIngredientPort;
import com.greenbowl.greenbowlserver.fridge.application.port.out.DeleteIngredientPort;
import com.greenbowl.greenbowlserver.fridge.application.port.out.GetIngredientPort;
import com.greenbowl.greenbowlserver.fridge.application.port.out.UpdateIngredientPort;
import com.greenbowl.greenbowlserver.fridge.domain.CategoryItem;
import com.greenbowl.greenbowlserver.fridge.domain.Ingredient;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class IngredientPersistenceAdapter implements
        CreateIngredientPort, DeleteIngredientPort, GetIngredientPort, UpdateIngredientPort {
    private final IngredientJpaRepository ingredientJpaRepository;
    private final CategoryItemJpaRepository categoryItemJpaRepository;

    @Override
    public List<Ingredient> saveIngredient(Long userId, List<Ingredient> ingredients) {
        List<CategoryItemJpaEntity> defaultCategoryItems = categoryItemJpaRepository
                .findAllByUserIdNullAndDeleteYnFalseAndIsDefaultTrue();
        Map<Long, CategoryItemJpaEntity> defaultCategoryMap = defaultCategoryItems.stream()
                .collect(Collectors.toMap(CategoryItemJpaEntity::getId, Function.identity()));

        List<Long> categoryIds = ingredients.stream()
                .map(Ingredient::getCategoryId)
                .distinct()
                .collect(Collectors.toList());
        Map<Long, CategoryItemJpaEntity> categoryMap = categoryItemJpaRepository
                .findAllByUserIdAndIdIn(userId, categoryIds)
                .stream()
                .collect(Collectors.toMap(CategoryItemJpaEntity::getId, Function.identity()));

        Map<Long, CategoryItemJpaEntity> combinedCategoryMap = new HashMap<>();
        combinedCategoryMap.putAll(defaultCategoryMap);
        combinedCategoryMap.putAll(categoryMap);

        List<IngredientJpaEntity> entities = ingredients.stream()
                .map(ingredient -> {
                    CategoryItemJpaEntity categoryItemJpaEntity = combinedCategoryMap.get(ingredient.getCategoryId());
                    if (categoryItemJpaEntity == null) {
                        throw new IllegalArgumentException("카테고리를 찾을 수 없음: " + ingredient.getCategoryId());
                    }
                    return IngredientJpaEntity.from(ingredient, categoryItemJpaEntity, userId);
                })
                .collect(Collectors.toList());

        List<IngredientJpaEntity> savedEntities = ingredientJpaRepository.saveAll(entities);

        return savedEntities.stream()
                .map(FridgeJpaEntityToDomainMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Ingredient> getIngredientsByUserId(Long userId) {
        List<IngredientJpaEntity> ingredientEntities = ingredientJpaRepository
                .findAllByUserIdAndDeleteYnFalse(userId);

        return ingredientEntities.stream()
                .map(FridgeJpaEntityToDomainMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<IngredientResult> updateIngredients(Long userId,
                                                    List<Ingredient> ingredients) {

        List<IngredientJpaEntity> updateEntities = ingredients.stream()
                .map(ingredient -> {
                    IngredientJpaEntity existingEntity = ingredientJpaRepository.findById(ingredient.getId())
                            .orElseThrow(() -> new IllegalArgumentException("재료를 찾을 수 없음: " + ingredient.getId()));

                    CategoryItemJpaEntity categoryItemJpaEntity = categoryItemJpaRepository
                            .findByIdAndDeleteYnFalse(ingredient.getId())
                            .orElseThrow(()-> new IllegalArgumentException("카테고리를 찾을 수 없음" + ingredient.getCategoryId()));

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
            IngredientJpaEntity entity = ingredientJpaRepository.findByIdAndDeleteYnFalse(command.getId())
                    .orElseThrow(() -> new IllegalArgumentException("재료를 찾을 수 없음: " + command.getId()));
            entity.deleteIngredient();
        });
    }
}
