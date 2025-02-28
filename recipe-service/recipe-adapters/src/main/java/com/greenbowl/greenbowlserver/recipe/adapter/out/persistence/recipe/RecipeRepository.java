package com.greenbowl.greenbowlserver.recipe.adapter.out.persistence.recipe;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<RecipeJpaEntity, Long> {
    List<RecipeJpaEntity> findByNameAndDeleteYnFalse(String name);

    RecipeJpaEntity findByIdAndDeleteYnFalse(Long id);
}
