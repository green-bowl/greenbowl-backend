package com.greenbowl.greenbowlserver.fridge.domain;

import com.greenbowl.greenbowlserver.fridge.domain.wrapper.Quantity;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.StorageMethod;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Ingredient {
    private Long id;
    private Quantity quantity;
    private StorageMethod storageMethod;
    private LocalDate expirationDate;
    private Long categoryId;

    @Builder
    public Ingredient(Long id, Quantity quantity, StorageMethod storageMethod, LocalDate expirationDate, Long categoryId) {
        this.id = id;
        this.quantity = quantity;
        this.storageMethod = storageMethod;
        this.expirationDate = expirationDate;
        this.categoryId = categoryId;
    }

    public void update(int quantity, String storageMethod, LocalDate expirationDate, Long categoryId) {
        this.quantity = Quantity.of(quantity);
        this.storageMethod = StorageMethod.of(storageMethod);
        this.expirationDate = expirationDate;
        this.categoryId = categoryId;
    }
}
