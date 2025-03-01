package com.greenbowl.greenbowlserver.fridge.application.port.out;

import com.greenbowl.greenbowlserver.fridge.domain.DefaultIngredient;

import java.util.List;

public interface CreateDefaultIngredientPort {
    List<DefaultIngredient> saveDefaultIngredient(Long userId, List<DefaultIngredient> defaultIngredients);
}
