package com.greenbowl.greenbowlserver.user.port.in.usecase;

import com.greenbowl.greenbowlserver.user.port.in.AuthenticationResult;
import com.greenbowl.greenbowlserver.user.port.in.command.SignInCommand;

public interface AuthenticationUseCase {
    AuthenticationResult signInUser(SignInCommand signInCommand);
}
