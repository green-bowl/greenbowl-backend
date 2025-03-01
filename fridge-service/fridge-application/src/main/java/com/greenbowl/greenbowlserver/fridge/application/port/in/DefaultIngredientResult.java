package com.greenbowl.greenbowlserver.fridge.application.port.in;

import com.greenbowl.greenbowlserver.fridge.domain.DefaultCategoryItem;
import com.greenbowl.greenbowlserver.fridge.domain.DefaultIngredient;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.WrapperAccessor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter
public class DefaultIngredientResult {
    private final Long id;
    private final int quantity;
    private final String storageMethod;
    private final LocalDate expirationDate;
    private final Long categoryId;
    private final int sequence;
    private final String categoryDetail;

    public static DefaultIngredientResult from(DefaultIngredient ingredient, DefaultCategoryItem categoryItem) {
        return DefaultIngredientResult.builder()
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
