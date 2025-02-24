package com.greenbowl.greenbowlserver.fridge.adapter.in.web.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class DeleteCategoryItemRequest {
    @NotNull
    private Long id;
}
