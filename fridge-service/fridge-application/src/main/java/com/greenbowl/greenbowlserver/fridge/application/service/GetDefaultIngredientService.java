package com.greenbowl.greenbowlserver.fridge.application.service;

import com.greenbowl.greenbowlserver.fridge.application.port.in.DefaultIngredientResult;
import com.greenbowl.greenbowlserver.fridge.application.port.in.usecase.GetDefaultIngredientUseCase;
import com.greenbowl.greenbowlserver.fridge.application.port.out.GetDefaultCategoryItemPort;
import com.greenbowl.greenbowlserver.fridge.application.port.out.GetDefaultIngredientPort;
import com.greenbowl.greenbowlserver.fridge.domain.DefaultCategoryItem;
import com.greenbowl.greenbowlserver.fridge.domain.DefaultIngredient;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.WrapperAccessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetDefaultIngredientService implements GetDefaultIngredientUseCase {
    private final GetDefaultCategoryItemPort getDefaultCategoryItemPort;
    private final GetDefaultIngredientPort getDefaultIngredientPort;


    @Override
    public List<DefaultIngredientResult> getDefaultIngredients(Long userId) {
        List<DefaultIngredient> defaultIngredients = getDefaultIngredientPort.getDefaultIngredientsByUserId(userId);
        List<DefaultCategoryItem> defaultCategoryItems = getDefaultCategoryItemPort.getAllCategoryItems();

        Map<Long, DefaultCategoryItem> categoryItemMap = defaultCategoryItems.stream()
                .collect(Collectors.toMap(DefaultCategoryItem::getId, item -> item));

        return defaultIngredients.stream()
                .map(ingredient -> {
                    DefaultCategoryItem defaultCategoryItem = categoryItemMap.get(ingredient.getDefaultCategoryId());
                    return DefaultIngredientResult.builder()
                            .id(ingredient.getId())
                            .quantity(WrapperAccessor.getQuantity(ingredient.getQuantity()))
                            .categoryDetail(WrapperAccessor.getCategoryDetail(defaultCategoryItem.getCategoryDetail()))
                            .sequence(defaultCategoryItem.getCategory().getSequence())
                            .categoryId(defaultCategoryItem.getId())
                            .storageMethod(ingredient.getStorageMethod().getDescription())
                            .expirationDate(ingredient.getExpirationDate())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
