package com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.categoryitem;

import com.greenbowl.greenbowlserver.fridge.domain.wrapper.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryItemJpaRepository extends JpaRepository<CategoryItemJpaEntity, Long> {
    CategoryItemJpaEntity findByUserIdAndDeleteYnFalse(Long userId);
    List<CategoryItemJpaEntity> findAllByUserIdAndDeleteYnFalse(Long userId);

    CategoryItemJpaEntity findByUserIdAndIdAndDeleteYnFalse(Long userId, Long categoryId);

    Optional<CategoryItemJpaEntity> findByCategoryDetail(String categoryDetail);

    List<CategoryItemJpaEntity> findAllByUserIdAndCategoryAndDeleteYnFalse(Long userId, Category category);
}
