package com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.defaultingredient;

import com.greenbowl.greenbowlserver.common.adapter.out.persistence.audit.BaseGeneralEntity;
import com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.defaultcategoryitem.DefaultCategoryItemJpaEntity;
import com.greenbowl.greenbowlserver.fridge.domain.DefaultIngredient;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.Quantity;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.StorageMethod;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "default_ingredient")
@Entity(name = "default_ingredient")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DefaultIngredientJpaEntity extends BaseGeneralEntity {

    @Column(nullable = false)
    private Long userId;

    @Convert(converter = Quantity.QuantityConverter.class)
    @Column(nullable = false)
    private Quantity quantity;

    @Column(nullable = false)
    private StorageMethod storageMethod;

    @Column(nullable = false)
    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "default_category_item_id", nullable = false)
    private DefaultCategoryItemJpaEntity defaultCategoryItemJpaEntity;

    public void deleteDefaultIngredient(){
        super.deleteEntity();
    }

    @Builder
    public DefaultIngredientJpaEntity(Long userId, Quantity quantity, StorageMethod storageMethod, LocalDate expirationDate, DefaultCategoryItemJpaEntity defaultCategoryItemJpaEntity) {
        this.userId = userId;
        this.quantity = quantity;
        this.storageMethod = storageMethod;
        this.expirationDate = expirationDate;
        this.defaultCategoryItemJpaEntity = defaultCategoryItemJpaEntity;
    }

    public DefaultIngredientJpaEntity update(DefaultIngredient defaultIngredient, DefaultCategoryItemJpaEntity defaultCategoryItemJpaEntity) {
        this.quantity = defaultIngredient.getQuantity();
        this.storageMethod = defaultIngredient.getStorageMethod();
        this.expirationDate = defaultIngredient.getExpirationDate();
        this.defaultCategoryItemJpaEntity = defaultCategoryItemJpaEntity;
        return this;
    }

    public static DefaultIngredientJpaEntity from(Long userId, DefaultIngredient defaultIngredient, DefaultCategoryItemJpaEntity defaultCategoryItemJpaEntity) {
        Quantity quantity = defaultIngredient.getQuantity();
        LocalDate expirationDate = defaultIngredient.getExpirationDate();
        StorageMethod storageMethod = defaultIngredient.getStorageMethod();

        return DefaultIngredientJpaEntity.builder()
                .userId(userId)
                .storageMethod(storageMethod)
                .quantity(quantity)
                .expirationDate(expirationDate)
                .defaultCategoryItemJpaEntity(defaultCategoryItemJpaEntity)
                .build();
    }
}
