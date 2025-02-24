package com.greenbowl.greenbowlserver.fridge.application.port.out;

import com.greenbowl.greenbowlserver.fridge.domain.Ingredient;

import java.util.List;

public interface CreateIngredientPort {
    List<Ingredient> saveIngredient(List<Ingredient> ingredients);
}
