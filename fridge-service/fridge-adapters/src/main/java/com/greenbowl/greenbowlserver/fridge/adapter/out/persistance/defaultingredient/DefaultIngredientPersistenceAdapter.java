package com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.defaultingredient;

import com.greenbowl.greenbowlserver.fridge.adapter.out.mapper.FridgeJpaEntityToDomainMapper;
import com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.defaultcategoryitem.DefaultCategoryItemJpaEntity;
import com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.defaultcategoryitem.DefaultCategoryItemJpaRepository;
import com.greenbowl.greenbowlserver.fridge.application.port.in.DefaultIngredientResult;
import com.greenbowl.greenbowlserver.fridge.application.port.out.CreateDefaultIngredientPort;
import com.greenbowl.greenbowlserver.fridge.application.port.out.DeleteDefaultIngredientPort;
import com.greenbowl.greenbowlserver.fridge.application.port.out.GetDefaultIngredientPort;
import com.greenbowl.greenbowlserver.fridge.application.port.out.UpdateDefaultIngredientPort;
import com.greenbowl.greenbowlserver.fridge.domain.DefaultCategoryItem;
import com.greenbowl.greenbowlserver.fridge.domain.DefaultIngredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DefaultIngredientPersistenceAdapter implements
        GetDefaultIngredientPort, CreateDefaultIngredientPort, DeleteDefaultIngredientPort, UpdateDefaultIngredientPort {
    private final DefaultCategoryItemJpaRepository defaultCategoryItemJpaRepository;
    private final DefaultIngredientJpaRepository defaultIngredientJpaRepository;

    @Override
    public List<DefaultIngredient> saveDefaultIngredient(Long userId, List<DefaultIngredient> defaultIngredients) {
        List<DefaultIngredientJpaEntity> entities = defaultIngredients.stream()
                .map(defaultIngredient -> {
                    DefaultCategoryItemJpaEntity defaultCategoryItemJpaEntity = defaultCategoryItemJpaRepository
                            .findById(defaultIngredient.getDefaultCategoryId())
                            .orElseThrow(()-> new IllegalArgumentException("카테고리를 찾을 수 없음"));
                    return DefaultIngredientJpaEntity.from(userId, defaultIngredient, defaultCategoryItemJpaEntity);
                })
                .collect(Collectors.toList());

        List<DefaultIngredientJpaEntity> savedEntities = defaultIngredientJpaRepository.saveAll(entities);

        return savedEntities.stream()
                .map(FridgeJpaEntityToDomainMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<DefaultIngredient> getDefaultIngredientsByUserId(Long userId) {
        List<DefaultCategoryItemJpaEntity> defaultCategoryItemJpaEntities = defaultCategoryItemJpaRepository.findAllByDeleteYnFalse();

        List<Long> categoryIds = defaultCategoryItemJpaEntities.stream()
                .map(DefaultCategoryItemJpaEntity::getId)
                .collect(Collectors.toList());

        List<DefaultIngredientJpaEntity> defaultIngredientJpaEntities = defaultIngredientJpaRepository
                .findAllByDefaultCategoryItemJpaEntityIdInAndUserIdAndDeleteYnFalse(categoryIds, userId);

        return defaultIngredientJpaEntities.stream()
                .map(FridgeJpaEntityToDomainMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<DefaultIngredientResult> updateDefaultIngredients(List<DefaultIngredient> defaultIngredients) {
        List<DefaultCategoryItemJpaEntity> defaultCategoryItemJpaEntities = defaultCategoryItemJpaRepository
                .findAllByDeleteYnFalse();

        Map<Long, DefaultCategoryItemJpaEntity> defaultCategoryItemJpaEntityMap = defaultCategoryItemJpaEntities.stream()
                .collect(Collectors.toMap(DefaultCategoryItemJpaEntity::getId, entity -> entity));

        List<DefaultIngredientJpaEntity> defaultIngredientJpaEntities = defaultIngredients.stream()
                .map(defaultIngredient -> {
                    DefaultIngredientJpaEntity existingEntity = defaultIngredientJpaRepository.findById(defaultIngredient.getId())
                            .orElseThrow(() -> new IllegalArgumentException("재료를 찾을 수 없음: " + defaultIngredient.getId()));

                    DefaultCategoryItemJpaEntity defaultCategoryItemJpaEntity = defaultCategoryItemJpaEntityMap.get(defaultIngredient.getDefaultCategoryId());

                    if (defaultCategoryItemJpaEntity == null) {
                        throw new IllegalArgumentException("해당 사용자에 대한 카테고리를 찾을 수 없음: " + defaultIngredient.getDefaultCategoryId());
                    }

                    existingEntity.update(defaultIngredient, defaultCategoryItemJpaEntity);

                    return existingEntity;
                })
                .collect(Collectors.toList());

        return defaultIngredientJpaEntities.stream()
                .map(defaultIngredientJpaEntity -> {
                    DefaultIngredient defaultIngredient = FridgeJpaEntityToDomainMapper.mapToDomainEntity(defaultIngredientJpaEntity);
                    DefaultCategoryItem defaultCategoryItem = FridgeJpaEntityToDomainMapper.mapToDomainEntity(defaultIngredientJpaEntity.getDefaultCategoryItemJpaEntity());
                    return DefaultIngredientResult.from(defaultIngredient, defaultCategoryItem);
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDefaultIngredient(Long userId, List<DefaultIngredient> defaultIngredients) {
        List<Long> ids = defaultIngredients.stream()
                .map(DefaultIngredient::getId)
                .collect(Collectors.toList());

        List<DefaultIngredientJpaEntity> entities = defaultIngredientJpaRepository
                .findAllByIdInAndUserIdAndDeleteYnFalse(ids, userId);

        entities.forEach(DefaultIngredientJpaEntity::deleteDefaultIngredient);

        defaultIngredientJpaRepository.saveAll(entities);
    }
}
