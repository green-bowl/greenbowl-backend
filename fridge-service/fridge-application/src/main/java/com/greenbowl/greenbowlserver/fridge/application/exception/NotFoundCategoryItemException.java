package com.greenbowl.greenbowlserver.fridge.application.exception;

import com.greenbowl.greenbowlserver.common.domain.exception.illegalargument.novalue.NoValueException;

public class NotFoundCategoryItemException extends NoValueException {
    public NotFoundCategoryItemException(String message) {super(message);}
}
