package com.greenbowl.greenbowlserver.common.domain.excpeption.illegalargument.numberformat;

public class ParsingByteException extends NumberFormatException {
    public ParsingByteException(String message) {
        super(message);
    }
}