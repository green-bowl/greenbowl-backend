package com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.ingredient;

import com.greenbowl.greenbowlserver.common.adapter.out.persistence.audit.BaseGeneralEntity;
import com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.categoryitem.CategoryItemJpaEntity;
import com.greenbowl.greenbowlserver.fridge.domain.Ingredient;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.Quantity;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.StorageMethod;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "ingredient")
@Entity(name = "ingredient")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class IngredientJpaEntity extends BaseGeneralEntity {
    @Convert(converter = Quantity.QuantityConverter.class)
    @Column(nullable = false)
    private Quantity quantity;

    @Column(nullable = false)
    private StorageMethod storageMethod;

    @Column(nullable = false)
    private LocalDate expirationDate;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "category_item_id", nullable = false)
    private CategoryItemJpaEntity categoryItemJpaEntity;

    public void deleteIngredient(){
        super.deleteEntity();
    }

    @Builder
    public IngredientJpaEntity(Quantity quantity, StorageMethod storageMethod, LocalDate expirationDate, CategoryItemJpaEntity categoryItemJpaEntity, Long userId) {
        this.quantity = quantity;
        this.storageMethod = storageMethod;
        this.expirationDate = expirationDate;
        this.categoryItemJpaEntity = categoryItemJpaEntity;
        this.userId = userId;
    }

    public IngredientJpaEntity update(Ingredient ingredient, CategoryItemJpaEntity categoryItemJpaEntity) {
        this.quantity = ingredient.getQuantity();
        this.storageMethod = ingredient.getStorageMethod();
        this.expirationDate = ingredient.getExpirationDate();
        this.categoryItemJpaEntity = categoryItemJpaEntity;
        return this;
    }

    public static IngredientJpaEntity from(Ingredient ingredient, CategoryItemJpaEntity categoryItemJpaEntity, Long userId) {
        Quantity quantity = ingredient.getQuantity();
        LocalDate expirationDate = ingredient.getExpirationDate();
        StorageMethod storageMethod = ingredient.getStorageMethod();

        return IngredientJpaEntity.builder()
                .userId(userId)
                .storageMethod(storageMethod)
                .quantity(quantity)
                .expirationDate(expirationDate)
                .categoryItemJpaEntity(categoryItemJpaEntity)
                .build();
    }
}
