package com.greenbowl.greenbowlserver.fridge.adapter.in.web.response;

import com.greenbowl.greenbowlserver.fridge.domain.Ingredient;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.WrapperAccessor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class CreateIngredientResponse {
    private Long id;
    private int quantity;
    private String storageMethod;
    public static List<CreateIngredientResponse> from(List<Ingredient> ingredients) {
        return ingredients.stream().map(
                ingredient ->
                CreateIngredientResponse.builder()
                        .id(ingredient.getId())
                        .quantity(WrapperAccessor.getQuantity(ingredient.getQuantity()))
                        .storageMethod(ingredient.getStorageMethod().getDescription())
                        .build()
        ).collect(Collectors.toList());
    }
}
