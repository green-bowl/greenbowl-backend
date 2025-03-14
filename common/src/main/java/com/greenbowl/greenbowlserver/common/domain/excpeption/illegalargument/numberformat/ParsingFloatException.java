package com.greenbowl.greenbowlserver.common.domain.excpeption.illegalargument.numberformat;

public class ParsingFloatException extends NumberFormatException {
    public ParsingFloatException(String message) {
        super(message);
    }
}
