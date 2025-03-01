package com.greenbowl.greenbowlserver.fridge.application.port.in.usecase;

import com.greenbowl.greenbowlserver.fridge.application.port.in.DefaultIngredientResult;

import java.util.List;

public interface GetDefaultIngredientUseCase {
    List<DefaultIngredientResult> getDefaultIngredients(Long userId);
}
