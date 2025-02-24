package com.greenbowl.greenbowlserver.fridge.adapter.in.web.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
public class UpdateIngredientRequest {
    @NotNull
    private Long id;
    @NotNull
    @Size(min = 1, max = 99)
    private int quantity;
    @NotNull
    private String storageMethod;
    @NotNull
    private LocalDateTime expirationDate;
    @NotNull
    private Long categoryId;
}
