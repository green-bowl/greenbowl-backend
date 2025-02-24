package com.greenbowl.greenbowlserver.fridge.adapter.in.web.mapper;

import com.greenbowl.greenbowlserver.fridge.adapter.in.web.request.*;
import com.greenbowl.greenbowlserver.fridge.application.port.in.command.*;

public class FridgeRequestToCommandMapper {

    public static CreateCategoryItemCommand mapToCommand(CreateCategoryItemRequest createCategoryItemRequest) {
        return CreateCategoryItemCommand.builder()
                .categoryDetail(createCategoryItemRequest.getCategoryDetail())
                .sequence(createCategoryItemRequest.getSequence())
                .build();
    }

    public static CreateIngredientCommand mapToCommand(CreateIngredientRequest createIngredientRequest) {
        return CreateIngredientCommand.builder()
                .categoryId(createIngredientRequest.getCategoryId())
                .storageMethod(createIngredientRequest.getStorageMethod())
                .quantity(createIngredientRequest.getQuantity())
                .expirationDate(createIngredientRequest.getExpirationDate())
                .build();
    }

    public static DeleteCategoryItemCommand mapToCommand(DeleteCategoryItemRequest deleteCategoryItemRequest) {
        return DeleteCategoryItemCommand.builder()
                .id(deleteCategoryItemRequest.getId())
                .build();
    }

    public static UpdateIngredientCommand mapToCommand(UpdateIngredientRequest updateIngredientRequest) {
        return UpdateIngredientCommand.builder()
                .id(updateIngredientRequest.getId())
                .quantity(updateIngredientRequest.getQuantity())
                .storageMethod(updateIngredientRequest.getStorageMethod())
                .expirationDate(updateIngredientRequest.getExpirationDate())
                .categoryId(updateIngredientRequest.getCategoryId())
                .build();
    }

    public static DeleteIngredientCommand mapToCommand(DeleteIngredientRequest deleteIngredientRequest) {
        return DeleteIngredientCommand.builder()
                .id(deleteIngredientRequest.getId())
                .build();
    }
}
