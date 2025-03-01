package com.greenbowl.greenbowlserver.user.adapter.out.persistence.user;

import com.greenbowl.greenbowlserver.user.domain.wrapper.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserJpaEntity, Long> {
    boolean existsByEmailAndDeleteYnFalse(Email email);

    UserJpaEntity findByIdAndDeleteYnFalse(Long id);

    UserJpaEntity findByEmailAndDeleteYnFalse(Email email);
}
