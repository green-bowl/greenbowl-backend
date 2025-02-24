package com.greenbowl.greenbowlserver.fridge.adapter.in.web.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class CreateCategoryItemRequest {
    @NotNull
    private int sequence;
    @NotNull
    @Size(min = 1, max = 255)
    private String categoryDetail;
}
