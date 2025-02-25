package com.greenbowl.greenbowlserver.fridge.adapter.in.web.response;

import com.greenbowl.greenbowlserver.fridge.application.port.in.IngredientResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter
public class GetIngredientResponse {
    private Long id;
    private int quantity;
    private String storageMethod;
    private LocalDate expirationDate;
    private Long categoryId;
    private int sequence;
    private String categoryDetail;

    public static GetIngredientResponse from(IngredientResult ingredientResult) {
        return GetIngredientResponse.builder()
                .id(ingredientResult.getId())
                .quantity(ingredientResult.getQuantity())
                .storageMethod(ingredientResult.getStorageMethod())
                .expirationDate(ingredientResult.getExpirationDate())
                .categoryId(ingredientResult.getCategoryId())
                .sequence(ingredientResult.getSequence())
                .categoryDetail(ingredientResult.getCategoryDetail())
                .build();
    }
}
