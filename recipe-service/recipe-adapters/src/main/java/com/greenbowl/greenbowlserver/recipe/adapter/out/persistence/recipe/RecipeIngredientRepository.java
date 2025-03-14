package com.greenbowl.greenbowlserver.recipe.adapter.out.persistence.recipe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredientJpaEntity, Long> {
}
