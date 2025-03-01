package com.greenbowl.greenbowlserver.fridge.domain;

import com.greenbowl.greenbowlserver.fridge.domain.wrapper.Quantity;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.StorageMethod;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DefaultIngredient {
    private Long id;
    private Long userId;
    private Quantity quantity;
    private StorageMethod storageMethod;
    private LocalDate expirationDate;
    private Long defaultCategoryId;

    @Builder
    public DefaultIngredient(Long id, Long userId, Quantity quantity, StorageMethod storageMethod, LocalDate expirationDate, Long defaultCategoryId) {
        this.id = id;
        this.userId = userId;
        this.quantity = quantity;
        this.storageMethod = storageMethod;
        this.expirationDate = expirationDate;
        this.defaultCategoryId = defaultCategoryId;
    }

    public void update(int quantity, String storageMethod, LocalDate expirationDate, Long defaultCategoryId) {
        this.quantity = Quantity.of(quantity);
        this.storageMethod = StorageMethod.of(storageMethod);
        this.expirationDate = expirationDate;
        this.defaultCategoryId = defaultCategoryId;
    }
}
