package com.greenbowl.greenbowlserver.user.service;

import com.greenbowl.greenbowlserver.common.application.UseCase;
import com.greenbowl.greenbowlserver.user.domain.User;
import com.greenbowl.greenbowlserver.user.port.in.command.SignInCommand;
import com.greenbowl.greenbowlserver.user.port.in.usecase.SignUpUseCase;
import com.greenbowl.greenbowlserver.user.port.out.SignUpPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@RequiredArgsConstructor
@UseCase
@Transactional(isolation = SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
public class SignUpService implements SignUpUseCase {
    private final SignUpPort signUpPort;

    public User createUser(SignInCommand signInCommand) {
        return signUpPort.saveUser(User.of(signInCommand.getEmail()));
    }
}
