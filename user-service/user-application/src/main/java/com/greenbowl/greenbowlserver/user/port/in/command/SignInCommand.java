package com.greenbowl.greenbowlserver.user.port.in.command;

import lombok.Getter;

@Getter
public class SignInCommand {
    private final String email;

    private SignInCommand(String email) {
        this.email = email;
    }

    public static SignInCommand of(String email) {
        return new SignInCommand(email);
    }
}
