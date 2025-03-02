package com.greenbowl.greenbowlserver.recipe.adapter.out.persistence.recipe;

import com.greenbowl.greenbowlserver.recipe.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<RecipeJpaEntity, Long> {
    List<RecipeJpaEntity> findByUserIdAndDeleteYnFalseOrderByModifiedAtDesc(Long userId);

    List<Recipe> existsByNameAndDeleteYnFalse(String name);

    List<RecipeJpaEntity> findByNameAndDeleteYnFalse(String name);

    RecipeJpaEntity findByIdAndDeleteYnFalse(Long id);
}
