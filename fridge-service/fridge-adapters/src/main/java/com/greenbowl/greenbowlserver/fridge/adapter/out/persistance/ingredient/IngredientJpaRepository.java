package com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientJpaRepository extends JpaRepository<IngredientJpaEntity, Long> {
    List<IngredientJpaEntity> findAllByUserIdAndDeleteYnFalse(Long userId);

    Optional<IngredientJpaEntity> findByIdAndDeleteYnFalse(Long id);
}
