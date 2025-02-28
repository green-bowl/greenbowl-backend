package com.greenbowl.greenbowlserver.recipe.adapter.out.mapper;

import com.greenbowl.greenbowlserver.common.utility.FormatValidator;
import com.greenbowl.greenbowlserver.recipe.adapter.out.persistence.recipe.EmbeddableNutrition;
import com.greenbowl.greenbowlserver.recipe.adapter.out.persistence.recipe.RecipeIngredientJpaEntity;
import com.greenbowl.greenbowlserver.recipe.adapter.out.persistence.recipe.RecipeJpaEntity;
import com.greenbowl.greenbowlserver.recipe.domain.Nutrition;
import com.greenbowl.greenbowlserver.recipe.domain.Recipe;
import com.greenbowl.greenbowlserver.recipe.domain.RecipeIngredient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RecipeJpaEntityToDomainMapper {
    public static Optional<Recipe> mapToOptionalDomainEntity(RecipeJpaEntity recipeJpaEntity) {
        return Optional.ofNullable(recipeJpaEntity)
                .filter(FormatValidator::hasValue)
                .map(entity -> Recipe.of(
                        entity.getId(),
                        entity.getName(),
                        entity.getImageUrl(),
                        entity.getCookingTime(),
                        entity.getCalories(),
                        entity.getOneLineIntroduction(),
                        mapToDomainEntity(entity.getRecipeIngredients()),
                        entity.getIntroduction(),
                        mapToDomainEntity(entity.getNutrition()),
                        entity.getCreatedAt(),
                        entity.getModifiedAt()
                ));
    }

    public static Recipe mapToDomainEntity(RecipeJpaEntity recipeJpaEntity) {
        return Recipe.of(
                recipeJpaEntity.getId(),
                recipeJpaEntity.getName(),
                recipeJpaEntity.getImageUrl(),
                recipeJpaEntity.getCookingTime(),
                recipeJpaEntity.getCalories(),
                recipeJpaEntity.getOneLineIntroduction(),
                mapToDomainEntity(recipeJpaEntity.getRecipeIngredients()),
                recipeJpaEntity.getIntroduction(),
                mapToDomainEntity(recipeJpaEntity.getNutrition()),
                recipeJpaEntity.getCreatedAt(),
                recipeJpaEntity.getModifiedAt()
        );
    }

    private static List<RecipeIngredient> mapToDomainEntity(
            List<RecipeIngredientJpaEntity> recipeIngredientJpaEntities
    ) {
        return recipeIngredientJpaEntities.stream().map(
                        recipeIngredientJpaEntity -> RecipeIngredient.of(
                                recipeIngredientJpaEntity.getName(),
                                recipeIngredientJpaEntity.getWeight()
                        )
                )
                .collect(Collectors.toList());
    }

    private static Nutrition mapToDomainEntity(EmbeddableNutrition embeddableNutrition) {
        if (FormatValidator.hasValue(embeddableNutrition)) {
            return Nutrition.of(
                    embeddableNutrition.getCarbohydrate(),
                    embeddableNutrition.getProtein(),
                    embeddableNutrition.getFat(),
                    embeddableNutrition.getSodium(),
                    embeddableNutrition.getSugar()
            );
        }

        return null;
    }
}
