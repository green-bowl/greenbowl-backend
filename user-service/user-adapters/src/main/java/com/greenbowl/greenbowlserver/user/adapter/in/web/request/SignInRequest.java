package com.greenbowl.greenbowlserver.user.adapter.in.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import static com.greenbowl.greenbowlserver.user.adapter.in.web.ApiConstant.EMAIL_EXAMPLE;

@Getter
public class SignInRequest {
    private static final String EMAIL_VALUE = "사용자 이메일 주소";

    @ApiModelProperty(value = EMAIL_VALUE, example = EMAIL_EXAMPLE)
    private String email;
}
