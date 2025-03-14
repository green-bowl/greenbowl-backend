package com.greenbowl.greenbowlserver.recipe.port.in.usecase;

import com.greenbowl.greenbowlserver.recipe.port.in.command.ModifyRecipeCommand;

public interface ModifyRecipeUseCase {
    void addDetailedRecipeInformation(ModifyRecipeCommand modifyRecipeCommand);
}
