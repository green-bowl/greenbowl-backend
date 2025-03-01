package com.greenbowl.greenbowlserver.user.adapter.out.persistence.user;

import com.greenbowl.greenbowlserver.common.adapter.out.PersistenceAdapter;
import com.greenbowl.greenbowlserver.user.adapter.out.mapper.UserJpaEntityToDomainMapper;
import com.greenbowl.greenbowlserver.user.domain.User;
import com.greenbowl.greenbowlserver.user.domain.wrapper.Email;
import com.greenbowl.greenbowlserver.user.port.out.FindUserPort;
import com.greenbowl.greenbowlserver.user.port.out.SignUpPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@PersistenceAdapter
@RequiredArgsConstructor
public class UserPersistenceAdapter implements SignUpPort, FindUserPort {
    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return UserJpaEntityToDomainMapper.mapToDomainEntity(userRepository.save(UserJpaEntity.from(user))).get();
    }

    @Override
    public boolean isUserExists(String email) {
        return userRepository.existsByEmailAndDeleteYnFalse(Email.of(email));
    }

    @Override
    public Optional<User> findById(Long userId) {
        UserJpaEntity userJpaEntity = userRepository.findByIdAndDeleteYnFalse(userId);

        return UserJpaEntityToDomainMapper.mapToDomainEntity(userJpaEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        UserJpaEntity userJpaEntity = userRepository.findByEmailAndDeleteYnFalse(Email.of(email));

        return UserJpaEntityToDomainMapper.mapToDomainEntity(userJpaEntity);
    }
}
