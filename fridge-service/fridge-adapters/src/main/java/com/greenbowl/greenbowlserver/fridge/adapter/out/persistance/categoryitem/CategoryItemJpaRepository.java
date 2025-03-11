package com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.categoryitem;

import com.greenbowl.greenbowlserver.fridge.domain.wrapper.Category;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.CategoryDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryItemJpaRepository extends JpaRepository<CategoryItemJpaEntity, Long> {
    List<CategoryItemJpaEntity> findAllByUserIdAndDeleteYnFalse(Long userId);

    Optional<CategoryItemJpaEntity> findByUserIdAndIdAndDeleteYnFalse(Long userId, Long categoryId);

    List<CategoryItemJpaEntity> findAllByUserIdAndCategoryAndDeleteYnFalse(Long userId, Category category);

    List<CategoryItemJpaEntity> findAllByUserIdAndIdIn(Long userId, List<Long> categoryIds);

    List<CategoryItemJpaEntity> findAllByUserIdNullAndDeleteYnFalseAndIsDefaultTrue();

    List<CategoryItemJpaEntity> findByIdInAndDeleteYnFalse(List<Long> categoryIds);

    boolean existsByCategoryDetailAndDeleteYnFalseAndCategory(CategoryDetail categoryDetail, Category category);

    Optional<CategoryItemJpaEntity> findByIdAndDeleteYnFalse(Long id);
}
