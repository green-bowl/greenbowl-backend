package com.greenbowl.greenbowlserver.user.port.out;

import com.greenbowl.greenbowlserver.user.domain.User;

public interface SignUpPort {
    User saveUser(User user);
}
