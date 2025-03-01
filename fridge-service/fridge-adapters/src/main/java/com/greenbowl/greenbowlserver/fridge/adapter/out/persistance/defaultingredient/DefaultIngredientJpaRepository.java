package com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.defaultingredient;

import com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.defaultcategoryitem.DefaultCategoryItemJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DefaultIngredientJpaRepository extends JpaRepository<DefaultIngredientJpaEntity, Long> {
    List<DefaultIngredientJpaEntity> findAllByDefaultCategoryItemJpaEntityAndUserIdAndDeleteYnFalse(
            DefaultCategoryItemJpaEntity defaultCategoryItemJpaEntity,
            Long userId);
    Optional<DefaultIngredientJpaEntity> findByIdAndUserIdAndDeleteYnFalse(Long id, Long userId);

    List<DefaultIngredientJpaEntity> findAllByDefaultCategoryItemJpaEntityIdInAndUserIdAndDeleteYnFalse(
            List<Long> categoryIds, Long userId);

    List<DefaultIngredientJpaEntity> findAllByIdInAndUserIdAndDeleteYnFalse(List<Long> ids, Long userId);
}
