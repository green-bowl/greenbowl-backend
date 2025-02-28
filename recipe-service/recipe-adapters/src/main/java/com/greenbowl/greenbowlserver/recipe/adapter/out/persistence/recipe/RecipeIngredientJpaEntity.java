package com.greenbowl.greenbowlserver.recipe.adapter.out.persistence.recipe;

import com.greenbowl.greenbowlserver.common.adapter.out.persistence.audit.BaseGeneralEntity;
import com.greenbowl.greenbowlserver.recipe.domain.RecipeIngredient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity(name = "recipe_ingredient")
@Table(name = "recipe_ingredient")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RecipeIngredientJpaEntity extends BaseGeneralEntity {
    @Column(nullable = false, length = 255)
    private String name;

    private short weight;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "recipe_id")
    private RecipeJpaEntity recipe;

    private RecipeIngredientJpaEntity(String name, short weight, RecipeJpaEntity recipeJpaEntity) {
        this.name = name;
        this.weight = weight;
        recipe = recipeJpaEntity;
    }

    public static RecipeIngredientJpaEntity from(RecipeIngredient recipeIngredient, RecipeJpaEntity recipeJpaEntity) {
        return new RecipeIngredientJpaEntity(recipeIngredient.getName(), recipeIngredient.getWeight(), recipeJpaEntity);
    }
}
