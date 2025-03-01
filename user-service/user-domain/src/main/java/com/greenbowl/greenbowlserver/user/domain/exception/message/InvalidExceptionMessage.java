package com.greenbowl.greenbowlserver.user.domain.exception.message;

public class InvalidExceptionMessage {
    public static final String INVALID_EMAIL_EXCEPTION = "이메일 주소 형식이 잘못되었습니다. 예: abcd@abc.com";

    public static final String INVALID_USERNAME_EXCEPTION
            = "아이디는 4~16자의 영소문자와 숫자만으로 구성되어야 합니다. 예: tester1";

    public static final String INVALID_PASSWORD_EXCEPTION
            = "비밀번호는 8자 이상이어야 하며, 하나 이상의 대문자와 소문자, 숫자, 특수문자를 포함해야 합니다. 예: Test1234!@";

    public static final String INVALID_FULL_NAME_EXCEPTION = "성명은 2~5자의 한글만 가능합니다. 예: 홍길동";
}
