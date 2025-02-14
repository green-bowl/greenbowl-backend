package com.greenbowl.greenbowlserver.common.excpeption.illegalargument.numberformat;

public class ParsingFloatException extends NumberFormatException {
    public ParsingFloatException(String message) {
        super(message);
    }
}