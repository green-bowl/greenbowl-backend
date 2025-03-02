package com.greenbowl.greenbowlserver.recipe.port.out;

public interface DeleteRecipePort {
    void deleteById(Long id);

    void deleteByName(String name);
}
