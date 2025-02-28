package com.greenbowl.greenbowlserver.recipe.port.out;

import com.greenbowl.greenbowlserver.recipe.domain.Recipe;

import java.util.List;
import java.util.Optional;

public interface FindRecipePort {
    Optional<Recipe> findById(Long id);

    List<Recipe> findByName(String name);
}
