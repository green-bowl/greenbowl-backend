package com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientJpaRepository extends JpaRepository<IngredientJpaEntity, Long> {
//    IngredientJpaEntity findByCategoryItemAndDeleteYnFalse(CategoryItemJpaEntity categoryItemJpaEntity);
    IngredientJpaEntity findByCategoryItemJpaEntity_IdAndDeleteYnFalse(Long categoryItemId);

    List<IngredientJpaEntity> findAllByCategoryItemJpaEntity_IdAndDeleteYnFalse(Long id);
}
