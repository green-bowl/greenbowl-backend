package com.greenbowl.greenbowlserver.common.domain.excpeption;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {
    private int statusCode;
    private String message;
}