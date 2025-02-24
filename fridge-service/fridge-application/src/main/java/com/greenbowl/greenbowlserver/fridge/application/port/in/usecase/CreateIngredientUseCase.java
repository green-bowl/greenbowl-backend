package com.greenbowl.greenbowlserver.fridge.application.port.in.usecase;

import com.greenbowl.greenbowlserver.fridge.application.port.in.command.CreateIngredientCommand;
import com.greenbowl.greenbowlserver.fridge.domain.Ingredient;

import java.util.List;

public interface CreateIngredientUseCase {
    List<Ingredient> createIngredient(List<CreateIngredientCommand> command);
}
