package com.greenbowl.greenbowlserver.user.adapter.out.mapper;

import com.greenbowl.greenbowlserver.common.utility.FormatValidator;
import com.greenbowl.greenbowlserver.user.adapter.out.persistence.user.UserJpaEntity;
import com.greenbowl.greenbowlserver.user.domain.User;

import java.util.Optional;

public class UserJpaEntityToDomainMapper {

    public static Optional<User> mapToDomainEntity(UserJpaEntity userJpaEntity) {
        return Optional.ofNullable(userJpaEntity)
                .filter(FormatValidator::hasValue)
                .map(entity -> User.builder()
                        .id(entity.getId())
                        .email(entity.getEmail())
                        .createdAt(entity.getCreatedAt())
                        .modifiedAt(entity.getModifiedAt())
                        .lastLoggedInAt(entity.getLastLoggedInAt())
                        .build());
    }
}
