package com.greenbowl.greenbowlserver.user.adapter.in.web.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.greenbowl.greenbowlserver.user.port.in.AuthenticationResult;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SignInResponse {
    private final Long userId;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private final LocalDateTime loggedInAt;

    private final boolean isNewUser;
    private final String accessToken;

    @Builder
    private SignInResponse(Long userId, String accessToken, boolean isNewUser, LocalDateTime loggedInAt) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.isNewUser = isNewUser;
        this.loggedInAt = loggedInAt;
    }

    public static SignInResponse from(AuthenticationResult authenticationResult) {
        return SignInResponse.builder()
                .userId(authenticationResult.getUserId())
                .loggedInAt(authenticationResult.getLastLoggedInAt())
                .isNewUser(authenticationResult.isNewUser())
                .accessToken(authenticationResult.getAccessToken())
                .build();
    }
}
