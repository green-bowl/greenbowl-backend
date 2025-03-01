package com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.categoryitem;

import com.greenbowl.greenbowlserver.fridge.domain.wrapper.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryItemJpaRepository extends JpaRepository<CategoryItemJpaEntity, Long> {
    CategoryItemJpaEntity findByUserIdAndDeleteYnFalse(Long userId);
    List<CategoryItemJpaEntity> findAllByUserIdAndDeleteYnFalse(Long userId);

    CategoryItemJpaEntity findByUserIdAndIdAndDeleteYnFalse(Long userId, Long categoryId);

    List<CategoryItemJpaEntity> findAllByUserIdAndCategoryAndDeleteYnFalse(Long userId, Category category);

    List<CategoryItemJpaEntity> findAllByUserIdAndIdIn(Long userId, List<Long> categoryIds);
}
