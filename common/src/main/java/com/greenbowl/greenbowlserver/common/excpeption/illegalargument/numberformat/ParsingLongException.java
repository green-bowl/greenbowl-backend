package com.greenbowl.greenbowlserver.common.excpeption.illegalargument.numberformat;

public class ParsingLongException extends NumberFormatException {
    public ParsingLongException(String message) {
        super(message);
    }
}