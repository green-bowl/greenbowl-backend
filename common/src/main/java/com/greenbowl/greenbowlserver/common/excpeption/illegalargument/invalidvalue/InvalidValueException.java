package com.greenbowl.greenbowlserver.common.excpeption.illegalargument.invalidvalue;

public abstract class InvalidValueException extends IllegalArgumentException {
    public InvalidValueException(String message) {
        super(message);
    }
}