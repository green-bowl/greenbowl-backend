package com.greenbowl.greenbowlserver.user.adapter.in.web.mapper;

import com.greenbowl.greenbowlserver.user.adapter.in.web.request.SignInRequest;
import com.greenbowl.greenbowlserver.user.port.in.command.SignInCommand;

public class UserRequestToCommandMapper {
    public static SignInCommand mapToCommand(SignInRequest signInRequest) {
        return SignInCommand.of(signInRequest.getEmail());
    }
}
