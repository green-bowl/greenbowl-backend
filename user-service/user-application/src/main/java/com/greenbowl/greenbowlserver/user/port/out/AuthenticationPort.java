package com.greenbowl.greenbowlserver.user.port.out;

import com.greenbowl.greenbowlserver.user.domain.User;
import org.springframework.security.core.Authentication;

public interface AuthenticationPort {
    String generateAccessToken(User user);

    Authentication getAuthentication(String token);

    String parseUserId(String accessToken);

    boolean validateToken(String accessToken);
}
