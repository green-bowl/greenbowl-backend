package com.greenbowl.greenbowlserver.recipe.adapter.out.persistence.recipe;

import com.greenbowl.greenbowlserver.common.adapter.out.persistence.audit.BaseGeneralEntity;
import com.greenbowl.greenbowlserver.recipe.domain.RecipIngredient;
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

    private RecipeIngredientJpaEntity(String name, short weight) {
        this.name = name;
        this.weight = weight;
    }

    public static RecipeIngredientJpaEntity from(RecipIngredient recipIngredient) {
        return new RecipeIngredientJpaEntity(recipIngredient.getName(), recipIngredient.getWeight());
    }
}
