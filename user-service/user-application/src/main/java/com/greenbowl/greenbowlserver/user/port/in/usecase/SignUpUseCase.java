package com.greenbowl.greenbowlserver.user.port.in.usecase;

import com.greenbowl.greenbowlserver.user.domain.User;
import com.greenbowl.greenbowlserver.user.port.in.command.SignInCommand;

public interface SignUpUseCase {
    User createUser(SignInCommand signInCommand);
}
