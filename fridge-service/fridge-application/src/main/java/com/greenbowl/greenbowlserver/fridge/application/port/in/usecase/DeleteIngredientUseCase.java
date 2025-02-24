package com.greenbowl.greenbowlserver.fridge.application.port.in.usecase;

import com.greenbowl.greenbowlserver.fridge.application.port.in.command.DeleteIngredientCommand;

import java.util.List;

public interface DeleteIngredientUseCase {
    void deleteIngredient(List<DeleteIngredientCommand> deleteIngredientCommands);
}
