package com.greenbowl.greenbowlserver.fridge.adapter.in.web.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class DeleteIngredientRequest {
    @NotNull
    private Long id;
}
