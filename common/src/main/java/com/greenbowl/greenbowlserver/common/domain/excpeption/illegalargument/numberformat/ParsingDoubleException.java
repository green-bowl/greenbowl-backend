package com.greenbowl.greenbowlserver.common.domain.excpeption.illegalargument.numberformat;

public class ParsingDoubleException extends NumberFormatException {
    public ParsingDoubleException(String message) {
        super(message);
    }
}
