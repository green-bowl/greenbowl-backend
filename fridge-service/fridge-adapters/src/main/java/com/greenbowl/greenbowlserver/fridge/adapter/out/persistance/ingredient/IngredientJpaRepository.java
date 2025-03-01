package com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.ingredient;

import com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.categoryitem.CategoryItemJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientJpaRepository extends JpaRepository<IngredientJpaEntity, Long> {
    List<IngredientJpaEntity> findAllByCategoryItemJpaEntityAndDeleteYnFalse(CategoryItemJpaEntity categoryItemJpaEntity);

    List<IngredientJpaEntity> findAllByCategoryItemJpaEntityIdInAndDeleteYnFalse(List<Long> categoryIds);
}
