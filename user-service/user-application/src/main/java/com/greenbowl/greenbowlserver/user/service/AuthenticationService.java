package com.greenbowl.greenbowlserver.user.service;

import com.greenbowl.greenbowlserver.common.application.UseCase;
import com.greenbowl.greenbowlserver.user.domain.User;
import com.greenbowl.greenbowlserver.user.port.in.AuthenticationResult;
import com.greenbowl.greenbowlserver.user.port.in.command.SignInCommand;
import com.greenbowl.greenbowlserver.user.port.in.usecase.AuthenticationUseCase;
import com.greenbowl.greenbowlserver.user.port.in.usecase.GetUserUseCase;
import com.greenbowl.greenbowlserver.user.port.in.usecase.SignUpUseCase;
import com.greenbowl.greenbowlserver.user.port.out.AuthenticationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@RequiredArgsConstructor
@UseCase
@Transactional(isolation = READ_COMMITTED, timeout = 15)
public class AuthenticationService implements AuthenticationUseCase {
    private final GetUserUseCase getUserUseCase;
    private final SignUpUseCase signUpUseCase;
    private final AuthenticationPort authenticationPort;

    @Override
    public AuthenticationResult signInUser(SignInCommand signInCommand) {
        boolean isUserExists = getUserUseCase.isSignedUp(signInCommand.getEmail());
        User signedUpUser = loadUser(isUserExists, signInCommand);
        String accessToken = authenticationPort.generateAccessToken(signedUpUser);

        return AuthenticationResult.from(signedUpUser, !isUserExists, accessToken);
    }

    private User loadUser(boolean isUserExists, SignInCommand signInCommand) {
        if (isUserExists) {
            return getUserUseCase.getUser(signInCommand.getEmail());
        }

        return signUpUseCase.createUser(signInCommand);
    }
}
