package com.greenbowl.greenbowlserver.user.port.in;

import com.greenbowl.greenbowlserver.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
public class AuthenticationResult {
    private final Long userId;
    private final LocalDateTime lastLoggedInAt;
    private final boolean isNewUser;
    private final String accessToken;

    public static AuthenticationResult from(User user, boolean isNewUser, String accessToken) {
        return AuthenticationResult.builder()
                .userId(user.getId())
                .isNewUser(isNewUser)
                .lastLoggedInAt(user.getLastLoggedInAt())
                .accessToken(accessToken)
                .build();
    }
}
