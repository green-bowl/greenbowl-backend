package com.greenbowl.greenbowlserver.fridge.application.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class GetCategoryItemCommand {
    private Long userId;
}
