package com.greenbowl.greenbowlserver.user.port.out;

import com.greenbowl.greenbowlserver.user.domain.User;

import java.util.Optional;

public interface FindUserPort {
    boolean isUserExists(String email);

    Optional<User> findById(Long userId);

    Optional<User> findByEmail(String email);
}
