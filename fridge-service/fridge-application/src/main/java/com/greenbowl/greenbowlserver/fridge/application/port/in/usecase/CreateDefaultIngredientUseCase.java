package com.greenbowl.greenbowlserver.fridge.application.port.in.usecase;

import com.greenbowl.greenbowlserver.fridge.application.port.in.command.CreateIngredientCommand;
import com.greenbowl.greenbowlserver.fridge.domain.DefaultIngredient;

import java.util.List;

public interface CreateDefaultIngredientUseCase {
    List<DefaultIngredient> createDefaultIngredients(Long userId, List<CreateIngredientCommand> command);
}
