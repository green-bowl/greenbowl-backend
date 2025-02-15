package com.greenbowl.greenbowlserver.common.domain.excpeption.illegalargument.invalidvalue;

public class InvalidEmailException extends InvalidValueException {
    public InvalidEmailException(String message) {
        super(message);
    }
}