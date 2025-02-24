package com.greenbowl.greenbowlserver.fridge.application.port.out;

import com.greenbowl.greenbowlserver.fridge.application.port.in.command.DeleteIngredientCommand;

import java.util.List;

public interface DeleteIngredientPort {

    void deleteIngredient(List<DeleteIngredientCommand> deleteIngredientCommands);
}
