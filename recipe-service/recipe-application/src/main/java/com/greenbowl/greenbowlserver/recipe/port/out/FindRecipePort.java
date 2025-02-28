package com.greenbowl.greenbowlserver.recipe.port.out;

import com.greenbowl.greenbowlserver.recipe.domain.Recipe;

import java.util.List;
import java.util.Optional;

public interface FindRecipePort {
    List<Recipe> findByUserId(Long userId);

    List<Recipe> findByName(String name);

    Optional<Recipe> findById(Long id);
}
