package com.greenbowl.greenbowlserver.fridge.application.port.out;

import com.greenbowl.greenbowlserver.fridge.domain.DefaultIngredient;

import java.util.List;

public interface GetDefaultIngredientPort {
    List<DefaultIngredient> getDefaultIngredientsByUserId(Long userId);
}
