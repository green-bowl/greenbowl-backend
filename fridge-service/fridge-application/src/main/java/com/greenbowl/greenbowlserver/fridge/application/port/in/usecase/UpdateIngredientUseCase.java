package com.greenbowl.greenbowlserver.fridge.application.port.in.usecase;

import com.greenbowl.greenbowlserver.fridge.application.port.in.IngredientResult;
import com.greenbowl.greenbowlserver.fridge.application.port.in.command.UpdateIngredientCommand;

import java.util.List;

public interface UpdateIngredientUseCase {
    List<IngredientResult> updateIngredients(Long userId, List<UpdateIngredientCommand> updateIngredientCommands);
}
