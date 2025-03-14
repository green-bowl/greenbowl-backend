package com.greenbowl.greenbowlserver.recipe.adapter.out.persistence.recipe;

import com.greenbowl.greenbowlserver.common.adapter.out.persistence.audit.BaseGeneralEntity;
import com.greenbowl.greenbowlserver.common.utility.FormatValidator;
import com.greenbowl.greenbowlserver.recipe.domain.Recipe;
import com.greenbowl.greenbowlserver.recipe.domain.RecipeIngredient;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.CascadeType.*;

@Entity(name = "recipe")
@Table(name = "recipe")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RecipeJpaEntity extends BaseGeneralEntity {
    private Long userId;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 4_095)
    private String imageUrl;

    @Column(nullable = false)
    private short cookingTime;

    @Column(nullable = false)
    private short calories;

    @Column(length = 511)
    private String oneLineIntroduction;

    @OneToMany(mappedBy = "recipe", cascade = {PERSIST, MERGE, REMOVE})
    private List<RecipeIngredientJpaEntity> recipeIngredients;

    @Column(length = 65_535)
    private String introduction;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "carbohydrate", column = @Column(nullable = true)),
            @AttributeOverride(name = "protein", column = @Column(nullable = true)),
            @AttributeOverride(name = "fat", column = @Column(nullable = true)),
            @AttributeOverride(name = "sodium", column = @Column(nullable = true)),
            @AttributeOverride(name = "sugar", column = @Column(nullable = true))
    })
    private EmbeddableNutrition nutrition;

    @Builder
    private RecipeJpaEntity(
            Long userId, String name, String imageUrl, short cookingTime, short calories,
            String oneLineIntroduction, List<RecipeIngredientJpaEntity> recipeIngredientJpaEntities,
            String introduction, EmbeddableNutrition embeddableNutrition
    ) {
        this.userId = userId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.cookingTime = cookingTime;
        this.calories = calories;
        this.oneLineIntroduction = oneLineIntroduction;
        recipeIngredients = recipeIngredientJpaEntities;
        this.introduction = introduction;
        nutrition = embeddableNutrition;
    }

    public static RecipeJpaEntity from(Recipe recipe) {
        String recipeOneLineIntroduction = recipe.getOneLineIntroduction();

        if (FormatValidator.hasValue(recipeOneLineIntroduction)) {
            RecipeJpaEntity recipeJpaEntity = RecipeJpaEntity.builder()
                    .userId(recipe.getUserId())
                    .name(recipe.getName())
                    .imageUrl(recipe.getImageUrl())
                    .cookingTime(recipe.getCookingTime())
                    .calories(recipe.getCalories())
                    .oneLineIntroduction(recipe.getOneLineIntroduction())
                    .introduction(recipe.getIntroduction())
                    .embeddableNutrition(EmbeddableNutrition.from(recipe.getNutrition()))
                    .build();

            List<RecipeIngredientJpaEntity> recipeIngredientJpaEntities
                    = generateIngredientEntities(recipe.getRecipeIngredients(), recipeJpaEntity);
            recipeJpaEntity.recipeIngredients = recipeIngredientJpaEntities;

            return recipeJpaEntity;
        }

        return RecipeJpaEntity.builder()
                .userId(recipe.getUserId())
                .name(recipe.getName())
                .imageUrl(recipe.getImageUrl())
                .cookingTime(recipe.getCookingTime())
                .calories(recipe.getCalories())
                .build();
    }

    private static List<RecipeIngredientJpaEntity> generateIngredientEntities(
            List<RecipeIngredient> recipeIngredients, RecipeJpaEntity recipeJpaEntity
    ) {
        return recipeIngredients
                .stream()
                .map(recipeIngredient -> RecipeIngredientJpaEntity.from(
                        recipeIngredient, recipeJpaEntity
                ))
                .collect(Collectors.toList());
    }

    public void update(Recipe recipe) {
        this.oneLineIntroduction = recipe.getOneLineIntroduction();

        List<RecipeIngredientJpaEntity> recipeIngredientJpaEntities
                = generateIngredientEntities(recipe.getRecipeIngredients(), this);
        this.recipeIngredients.clear();
        this.recipeIngredients = recipeIngredientJpaEntities;
        this.introduction = recipe.getIntroduction();
        this.nutrition = EmbeddableNutrition.from(recipe.getNutrition());
    }

    public void delete() {
        deleteEntity();
    }
}
