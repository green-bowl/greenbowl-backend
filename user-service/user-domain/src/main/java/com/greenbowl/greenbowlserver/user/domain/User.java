package com.greenbowl.greenbowlserver.user.domain;

import com.greenbowl.greenbowlserver.user.domain.wrapper.Email;
import lombok.Builder;
import lombok.Getter;

import java.security.AuthProvider;
import java.time.LocalDateTime;

@Getter
public class User {
    private Long id;
    private Email email;
    private AuthProvider authProvider;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime lastLoggedInAt;

    private User(Email email) {
        this.email = email;
    }

    @Builder
    private User(
            Long id, Email email, AuthProvider authProvider,
            LocalDateTime createdAt, LocalDateTime modifiedAt, LocalDateTime lastLoggedInAt
    ) {
        this.id = id;
        this.email = email;
        this.authProvider = authProvider;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.lastLoggedInAt = lastLoggedInAt;
    }

    public static User of(String email) {
        return new User(Email.of(email));
    }
}
