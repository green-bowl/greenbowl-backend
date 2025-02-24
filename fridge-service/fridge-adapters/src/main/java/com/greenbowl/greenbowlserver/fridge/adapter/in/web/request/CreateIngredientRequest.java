package com.greenbowl.greenbowlserver.fridge.adapter.in.web.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
public class CreateIngredientRequest {
    @NotNull
    private Long categoryId;
    @NotNull
    @Size(min = 1, max = 99)
    private int quantity;
    @NotNull
    private String storageMethod;
    @NotNull
    private LocalDateTime expirationDate;
}
