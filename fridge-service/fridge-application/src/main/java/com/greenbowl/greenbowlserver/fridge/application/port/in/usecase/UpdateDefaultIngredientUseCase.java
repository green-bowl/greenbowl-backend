package com.greenbowl.greenbowlserver.fridge.application.port.in.usecase;

import com.greenbowl.greenbowlserver.fridge.application.port.in.DefaultIngredientResult;
import com.greenbowl.greenbowlserver.fridge.application.port.in.command.UpdateIngredientCommand;

import java.util.List;

public interface UpdateDefaultIngredientUseCase {
    List<DefaultIngredientResult> updateIngredients(Long userId, List<UpdateIngredientCommand> updateIngredientCommands);
}
