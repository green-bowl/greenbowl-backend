package com.greenbowl.greenbowlserver.common.domain.excpeption.illegalargument.invalidvalue;

public class ParsingLocalDateTimeException extends InvalidValueException {
    public ParsingLocalDateTimeException(String message) {
        super(message);
    }
}