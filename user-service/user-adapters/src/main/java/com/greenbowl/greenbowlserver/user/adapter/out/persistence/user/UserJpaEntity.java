package com.greenbowl.greenbowlserver.user.adapter.out.persistence.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.greenbowl.greenbowlserver.common.adapter.out.persistence.audit.BaseGeneralEntity;
import com.greenbowl.greenbowlserver.user.domain.User;
import com.greenbowl.greenbowlserver.user.domain.wrapper.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity(name = "user")
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserJpaEntity extends BaseGeneralEntity {
    @Convert(converter = Email.EmailConverter.class)
    @Column(nullable = false, unique = true, length = 60)
    @JsonIgnore
    private Email email;

    @LastModifiedDate
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime lastLoggedInAt;

    @Builder
    private UserJpaEntity(Email email, LocalDateTime lastLoggedInAt) {
        this.email = email;
        ;
        this.lastLoggedInAt = lastLoggedInAt;
    }

    public static UserJpaEntity from(User user) {
        return UserJpaEntity.builder()
                .email(user.getEmail())
                .lastLoggedInAt(LocalDateTime.now())
                .build();
    }

    public void updateLoginTime() {
        lastLoggedInAt = LocalDateTime.now();
    }
}
