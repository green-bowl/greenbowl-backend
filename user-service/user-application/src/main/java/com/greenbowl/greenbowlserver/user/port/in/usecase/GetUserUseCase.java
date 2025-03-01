package com.greenbowl.greenbowlserver.user.port.in.usecase;

import com.greenbowl.greenbowlserver.user.domain.User;

import java.util.Optional;

public interface GetUserUseCase {
    boolean isSignedUp(String email);

    User getUser(Long userId);

    User getUser(String email);
}
