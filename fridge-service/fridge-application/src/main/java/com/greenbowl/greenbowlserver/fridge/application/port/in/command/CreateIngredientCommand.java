package com.greenbowl.greenbowlserver.fridge.application.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter
public class CreateIngredientCommand {
    private Long categoryId;
    private int quantity;
    private String storageMethod;
    private LocalDate expirationDate;
}
