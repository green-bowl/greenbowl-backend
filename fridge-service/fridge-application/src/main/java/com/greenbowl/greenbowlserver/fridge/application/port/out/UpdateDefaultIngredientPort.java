package com.greenbowl.greenbowlserver.fridge.application.port.out;

import com.greenbowl.greenbowlserver.fridge.application.port.in.DefaultIngredientResult;
import com.greenbowl.greenbowlserver.fridge.domain.DefaultIngredient;

import java.util.List;

public interface UpdateDefaultIngredientPort {
    List<DefaultIngredientResult> updateDefaultIngredients(List<DefaultIngredient> defaultIngredients);
}
