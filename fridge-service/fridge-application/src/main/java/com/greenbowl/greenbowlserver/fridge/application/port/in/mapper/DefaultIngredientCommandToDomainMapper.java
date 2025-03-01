package com.greenbowl.greenbowlserver.fridge.application.port.in.mapper;

import com.greenbowl.greenbowlserver.fridge.application.port.in.command.CreateIngredientCommand;
import com.greenbowl.greenbowlserver.fridge.application.port.in.command.DeleteIngredientCommand;
import com.greenbowl.greenbowlserver.fridge.application.port.in.command.UpdateIngredientCommand;
import com.greenbowl.greenbowlserver.fridge.domain.DefaultIngredient;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.Quantity;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.StorageMethod;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultIngredientCommandToDomainMapper {

    public static DefaultIngredient mapToDomainEntity(CreateIngredientCommand createIngredientCommand) {
        return DefaultIngredient.builder()
                .defaultCategoryId(createIngredientCommand.getCategoryId())
                .quantity(Quantity.of(createIngredientCommand.getQuantity()))
                .storageMethod(StorageMethod.of(createIngredientCommand.getStorageMethod()))
                .expirationDate(createIngredientCommand.getExpirationDate())
                .build();
    }

    public static DefaultIngredient mapToDomainEntity(DeleteIngredientCommand deleteIngredientCommand) {
        return DefaultIngredient.builder()
                .id(deleteIngredientCommand.getId())
                .build();
    }

    public static DefaultIngredient mapToDomainEntity(UpdateIngredientCommand updateIngredientCommand) {
        return DefaultIngredient.builder()
                .id(updateIngredientCommand.getId())
                .storageMethod(StorageMethod.of(updateIngredientCommand.getStorageMethod()))
                .quantity(Quantity.of(updateIngredientCommand.getQuantity()))
                .defaultCategoryId(updateIngredientCommand.getCategoryId())
                .expirationDate(updateIngredientCommand.getExpirationDate())
                .build();
    }
}
