package com.greenbowl.greenbowlserver.fridge.adapter.in.web.response;

import com.greenbowl.greenbowlserver.fridge.application.port.in.IngredientResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class UpdateIngredientResponse {
    private Long id;
    private int quantity;
    private String storageMethod;
    private LocalDateTime expirationDate;
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
}
