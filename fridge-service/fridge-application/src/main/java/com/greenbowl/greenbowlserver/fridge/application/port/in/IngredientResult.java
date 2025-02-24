package com.greenbowl.greenbowlserver.fridge.application.port.in;

import com.greenbowl.greenbowlserver.fridge.domain.CategoryItem;
import com.greenbowl.greenbowlserver.fridge.domain.Ingredient;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.WrapperAccessor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
public class IngredientResult {
    private final Long id;
    private final int quantity;
    private final String storageMethod;
    private final LocalDateTime expirationDate;
    private final Long categoryId;
    private final int sequence;
    private final String categoryDetail;

    public static IngredientResult from(Ingredient ingredient, CategoryItem categoryItem) {
        return IngredientResult.builder()
                .id(ingredient.getId())
                .quantity(WrapperAccessor.getQuantity(ingredient.getQuantity()))
                .storageMethod(ingredient.getStorageMethod().getDescription())
                .expirationDate(ingredient.getExpirationDate())
                .categoryId(categoryItem.getId())
                .sequence(categoryItem.getCategory().getSequence())
                .categoryDetail(WrapperAccessor.getCategoryDetail(categoryItem.getCategoryDetail()))
                .build();
    }
}
