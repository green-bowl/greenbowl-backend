package com.greenbowl.greenbowlserver.fridge.adapter.in.web.response;

import com.greenbowl.greenbowlserver.fridge.domain.DefaultIngredient;
import com.greenbowl.greenbowlserver.fridge.domain.Ingredient;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.WrapperAccessor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateIngredientResponse {
    private Long id;
    private int quantity;
    private String storageMethod;

    public static CreateIngredientResponse from(Ingredient ingredient) {
        return CreateIngredientResponse.builder()
                        .id(ingredient.getId())
                        .quantity(WrapperAccessor.getQuantity(ingredient.getQuantity()))
                        .storageMethod(ingredient.getStorageMethod().getDescription())
                        .build();
    }

    public static CreateIngredientResponse from(DefaultIngredient ingredient) {
        return CreateIngredientResponse.builder()
                                .id(ingredient.getId())
                                .quantity(WrapperAccessor.getQuantity(ingredient.getQuantity()))
                                .storageMethod(ingredient.getStorageMethod().getDescription())
                                .build();
    }
}
