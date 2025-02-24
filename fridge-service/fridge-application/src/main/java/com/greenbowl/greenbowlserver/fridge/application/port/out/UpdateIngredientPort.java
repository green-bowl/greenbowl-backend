package com.greenbowl.greenbowlserver.fridge.application.port.out;

import com.greenbowl.greenbowlserver.fridge.application.port.in.IngredientResult;
import com.greenbowl.greenbowlserver.fridge.domain.Ingredient;

import java.util.List;

public interface UpdateIngredientPort {
    List<IngredientResult> updateIngredients(Long userId, List<Ingredient> ingredients);
}
