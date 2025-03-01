package com.greenbowl.greenbowlserver.fridge.application.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter
public class CreateDefaultIngredientCommand {
    private Long categoryId;
    private Long userId;
    private int quantity;
    private String storageMethod;
    private LocalDate expirationDate;
}
