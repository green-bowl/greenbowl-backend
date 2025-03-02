package com.greenbowl.greenbowlserver.recipe.adapter.out.persistence.recipe;

import com.greenbowl.greenbowlserver.recipe.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<RecipeJpaEntity, Long> {
    /*
    TODO: 회원 기능 구현 후 회원 ID 기반 조회 적용
    List<RecipeJpaEntity> findByUserIdAndDeleteYnFalseOrderByModifiedAtDesc(Long userId);
    */
    List<RecipeJpaEntity> findByDeleteYnFalseOrderByModifiedAtDesc();

    List<Recipe> existsByNameAndDeleteYnFalse(String name);

    List<RecipeJpaEntity> findByNameAndDeleteYnFalse(String name);

    RecipeJpaEntity findByIdAndDeleteYnFalse(Long id);
}
