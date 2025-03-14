package com.greenbowl.greenbowlserver.common.domain.excpeption.illegalargument.numberformat;

public class ParsingIntegerException extends NumberFormatException {
    public ParsingIntegerException(String message) {
        super(message);
    }
}
