package com.greenbowl.greenbowlserver.recipe.adapter.out.persistence.recipe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<RecipeJpaEntity, Long> {
}
