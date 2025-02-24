package com.greenbowl.greenbowlserver.fridge.application.port.in.mapper;

import com.greenbowl.greenbowlserver.fridge.application.port.in.command.CreateIngredientCommand;
import com.greenbowl.greenbowlserver.fridge.application.port.in.command.UpdateIngredientCommand;
import com.greenbowl.greenbowlserver.fridge.domain.Ingredient;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.Quantity;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.StorageMethod;

public class IngredientCommandToDomainMapper {
    public static Ingredient mapToDomainEntity(CreateIngredientCommand createIngredientCommand) {
        return Ingredient.builder()
                .categoryId(createIngredientCommand.getCategoryId())
                .quantity(Quantity.of(createIngredientCommand.getQuantity()))
                .storageMethod(StorageMethod.of(createIngredientCommand.getStorageMethod()))
                .expirationDate(createIngredientCommand.getExpirationDate())
                .build();
    }

    public static Ingredient mapToDomainEntity(UpdateIngredientCommand updateIngredientCommand) {
        return Ingredient.builder()
                .id(updateIngredientCommand.getId())
                .quantity(Quantity.of(updateIngredientCommand.getQuantity()))
                .categoryId(updateIngredientCommand.getCategoryId())
                .storageMethod(StorageMethod.of(updateIngredientCommand.getStorageMethod()))
                .expirationDate(updateIngredientCommand.getExpirationDate())
                .build();
    }
}
