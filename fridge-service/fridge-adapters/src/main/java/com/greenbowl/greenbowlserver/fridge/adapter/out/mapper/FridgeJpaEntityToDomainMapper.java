package com.greenbowl.greenbowlserver.fridge.adapter.out.mapper;

import com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.categoryitem.CategoryItemJpaEntity;
import com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.defaultcategoryitem.DefaultCategoryItemJpaEntity;
import com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.defaultingredient.DefaultIngredientJpaEntity;
import com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.ingredient.IngredientJpaEntity;
import com.greenbowl.greenbowlserver.fridge.domain.CategoryItem;
import com.greenbowl.greenbowlserver.fridge.domain.DefaultCategoryItem;
import com.greenbowl.greenbowlserver.fridge.domain.DefaultIngredient;
import com.greenbowl.greenbowlserver.fridge.domain.Ingredient;

public class FridgeJpaEntityToDomainMapper {

    public static CategoryItem mapToDomainEntity(CategoryItemJpaEntity categoryItemJpaEntity) {
        return CategoryItem.builder()
                .id(categoryItemJpaEntity.getId())
                .userId(categoryItemJpaEntity.getUserId())
                .category(categoryItemJpaEntity.getCategory())
                .categoryDetail(categoryItemJpaEntity.getCategoryDetail())
                .build();
    }

    public static Ingredient mapToDomainEntity(IngredientJpaEntity ingredientJpaEntity) {
        return Ingredient.builder()
                .id(ingredientJpaEntity.getId())
                .quantity(ingredientJpaEntity.getQuantity())
                .categoryId(ingredientJpaEntity.getCategoryItemJpaEntity().getId())
                .expirationDate(ingredientJpaEntity.getExpirationDate())
                .storageMethod(ingredientJpaEntity.getStorageMethod())
                .build();
    }

    public static DefaultCategoryItem mapToDomainEntity(DefaultCategoryItemJpaEntity defaultCategoryItemJpaEntity) {
        return DefaultCategoryItem.builder()
                .id(defaultCategoryItemJpaEntity.getId())
                .category(defaultCategoryItemJpaEntity.getCategory())
                .categoryDetail(defaultCategoryItemJpaEntity.getCategoryDetail())
                .build();
    }

    public static DefaultIngredient mapToDomainEntity(DefaultIngredientJpaEntity defaultIngredientJpaEntity) {
        return DefaultIngredient.builder()
                .id(defaultIngredientJpaEntity.getId())
                .userId(defaultIngredientJpaEntity.getUserId())
                .expirationDate(defaultIngredientJpaEntity.getExpirationDate())
                .quantity(defaultIngredientJpaEntity.getQuantity())
                .storageMethod(defaultIngredientJpaEntity.getStorageMethod())
                .defaultCategoryId(defaultIngredientJpaEntity.getDefaultCategoryItemJpaEntity().getId())
                .build();
    }
}
