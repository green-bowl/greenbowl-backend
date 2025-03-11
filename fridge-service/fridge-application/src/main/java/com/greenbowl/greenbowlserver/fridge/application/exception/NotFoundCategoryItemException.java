package com.greenbowl.greenbowlserver.fridge.application.exception;

import com.greenbowl.greenbowlserver.common.domain.excpeption.illegalargument.novalue.NoValueException;

public class NotFoundCategoryItemException extends NoValueException {
    public NotFoundCategoryItemException(String message) {super(message);}
}
