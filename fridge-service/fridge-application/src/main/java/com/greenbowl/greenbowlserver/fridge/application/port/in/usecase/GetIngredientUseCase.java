package com.greenbowl.greenbowlserver.fridge.application.port.in.usecase;

import com.greenbowl.greenbowlserver.fridge.application.port.in.IngredientResult;

import java.util.List;

public interface GetIngredientUseCase {
    List<IngredientResult> getIngredients(Long userId);
}
