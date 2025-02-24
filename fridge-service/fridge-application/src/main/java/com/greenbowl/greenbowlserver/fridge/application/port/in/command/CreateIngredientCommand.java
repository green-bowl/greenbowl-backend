package com.greenbowl.greenbowlserver.fridge.application.port.in.command;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
public class CreateIngredientCommand {
    private Long categoryId;
    private int quantity;
    private String storageMethod;
    private LocalDateTime expirationDate;
}
