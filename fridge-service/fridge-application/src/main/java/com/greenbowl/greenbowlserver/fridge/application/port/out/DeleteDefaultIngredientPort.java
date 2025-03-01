package com.greenbowl.greenbowlserver.fridge.application.port.out;

import com.greenbowl.greenbowlserver.fridge.domain.DefaultIngredient;

import java.util.List;

public interface DeleteDefaultIngredientPort {
    void deleteDefaultIngredient(Long userId, List<DefaultIngredient> defaultIngredients);
}
