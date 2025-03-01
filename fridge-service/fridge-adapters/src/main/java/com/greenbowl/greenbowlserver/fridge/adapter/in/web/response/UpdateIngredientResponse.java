package com.greenbowl.greenbowlserver.fridge.adapter.in.web.response;

import com.greenbowl.greenbowlserver.fridge.application.port.in.DefaultIngredientResult;
import com.greenbowl.greenbowlserver.fridge.application.port.in.IngredientResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder
public class UpdateIngredientResponse {
    private Long id;
    private int quantity;
    private String storageMethod;
    private LocalDate expirationDate;
    private int sequence;
    private String categoryDetail;

    public static UpdateIngredientResponse from(IngredientResult ingredientResult) {
        return UpdateIngredientResponse.builder()
                .id(ingredientResult.getId())
                .quantity(ingredientResult.getQuantity())
                .storageMethod(ingredientResult.getStorageMethod())
                .expirationDate(ingredientResult.getExpirationDate())
                .sequence(ingredientResult.getSequence())
                .categoryDetail(ingredientResult.getCategoryDetail())
                .build();
    }

    public static UpdateIngredientResponse from(DefaultIngredientResult defaultIngredientResult) {
        return UpdateIngredientResponse.builder()
                .id(defaultIngredientResult.getId())
                .quantity(defaultIngredientResult.getQuantity())
                .storageMethod(defaultIngredientResult.getStorageMethod())
                .expirationDate(defaultIngredientResult.getExpirationDate())
                .sequence(defaultIngredientResult.getSequence())
                .categoryDetail(defaultIngredientResult.getCategoryDetail())
                .build();
    }
}
