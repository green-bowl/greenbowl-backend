package com.greenbowl.greenbowlserver.fridge.application.port.in.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteCategoryItemCommand {
    private Long id;
}
