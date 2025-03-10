package com.greenbowl.greenbowlserver.fridge.application.service;

import com.greenbowl.greenbowlserver.common.application.UseCase;
import com.greenbowl.greenbowlserver.fridge.application.port.in.IngredientResult;
import com.greenbowl.greenbowlserver.fridge.application.port.in.usecase.GetIngredientUseCase;
import com.greenbowl.greenbowlserver.fridge.application.port.out.GetCategoryItemPort;
import com.greenbowl.greenbowlserver.fridge.application.port.out.GetIngredientPort;
import com.greenbowl.greenbowlserver.fridge.domain.CategoryItem;
import com.greenbowl.greenbowlserver.fridge.domain.Ingredient;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.WrapperAccessor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class GetIngredientService implements GetIngredientUseCase {
    private final GetIngredientPort getIngredientPort;
    private final GetCategoryItemPort getCategoryItemPort;

    @Override
    public List<IngredientResult> getIngredients(Long userId) {

        List<Ingredient> ingredients = getIngredientPort.getIngredientsByUserId(userId);

        List<Long> categoryIds = ingredients.stream()
                .map(Ingredient::getCategoryId)
                .collect(Collectors.toList());

        List<CategoryItem> categoryItems = getCategoryItemPort.getCategoryItemsByIds(categoryIds);

        Map<Long, CategoryItem> categoryItemMap = categoryItems.stream()
                .collect(Collectors.toMap(CategoryItem::getId, item -> item));

        return ingredients.stream()
                .map(ingredient -> {
                    CategoryItem categoryItem = categoryItemMap.get(ingredient.getCategoryId());
                    return IngredientResult.builder()
                            .id(ingredient.getId())
                            .quantity(WrapperAccessor.getQuantity(ingredient.getQuantity()))
                            .categoryDetail(WrapperAccessor.getCategoryDetail(categoryItem.getCategoryDetail()))
                            .sequence(categoryItem.getCategory().getSequence())
                            .categoryId(categoryItem.getId())
                            .storageMethod(ingredient.getStorageMethod().getDescription())
                            .expirationDate(ingredient.getExpirationDate())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
