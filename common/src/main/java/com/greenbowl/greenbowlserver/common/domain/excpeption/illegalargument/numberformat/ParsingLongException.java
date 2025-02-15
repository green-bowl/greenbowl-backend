package com.greenbowl.greenbowlserver.common.domain.excpeption.illegalargument.numberformat;

public class ParsingLongException extends NumberFormatException {
    public ParsingLongException(String message) {
        super(message);
    }
}