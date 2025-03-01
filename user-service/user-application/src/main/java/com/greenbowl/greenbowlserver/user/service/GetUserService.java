package com.greenbowl.greenbowlserver.user.service;

import com.greenbowl.greenbowlserver.common.application.UseCase;
import com.greenbowl.greenbowlserver.user.domain.User;
import com.greenbowl.greenbowlserver.user.domain.security.CustomUserDetails;
import com.greenbowl.greenbowlserver.user.exception.UserNotFoundException;
import com.greenbowl.greenbowlserver.user.port.in.usecase.GetUserUseCase;
import com.greenbowl.greenbowlserver.user.port.out.FindUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import static com.greenbowl.greenbowlserver.user.domain.exception.message.NotFoundExceptionMessage.EMAIL_NOT_FOUND_EXCEPTION_MESSAGE;
import static com.greenbowl.greenbowlserver.user.domain.exception.message.NotFoundExceptionMessage.USER_ID_NOT_FOUND_EXCEPTION_MESSAGE;
import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@RequiredArgsConstructor
@UseCase
@Transactional(isolation = READ_COMMITTED, readOnly = true, timeout = 10)
public class GetUserService implements UserDetailsService, GetUserUseCase {
    private final FindUserPort findUserPort;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return new CustomUserDetails(getUser(Long.parseLong(userId)));
    }

    @Override
    public boolean isSignedUp(String email) {
        return findUserPort.isUserExists(email);
    }

    @Override
    public User getUser(Long userId) {
        return findUserPort.findById(userId)
                .orElseThrow(
                        () -> new UserNotFoundException(String.format(USER_ID_NOT_FOUND_EXCEPTION_MESSAGE, userId))
                );
    }

    @Override
    public User getUser(String email) {
        return findUserPort.findByEmail(email)
                .orElseThrow(
                        () -> new UserNotFoundException(String.format(EMAIL_NOT_FOUND_EXCEPTION_MESSAGE, email))
                );
    }
}
