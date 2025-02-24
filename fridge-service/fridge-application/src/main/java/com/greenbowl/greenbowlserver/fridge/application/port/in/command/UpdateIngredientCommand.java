package com.greenbowl.greenbowlserver.fridge.application.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
public class UpdateIngredientCommand {
    private Long id;
    private int quantity;
    private String storageMethod;
    private LocalDateTime expirationDate;
    private Long categoryId;
}
