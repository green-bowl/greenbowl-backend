package com.greenbowl.greenbowlserver.fridge.adapter.in.web.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public class CreateCategoryItemRequest {
    @NotNull
    @Pattern(regexp = "^[1-8]$")
    private int sequence;
    @NotNull
    @Size(min = 1, max = 255)
    private String categoryDetail;
}
