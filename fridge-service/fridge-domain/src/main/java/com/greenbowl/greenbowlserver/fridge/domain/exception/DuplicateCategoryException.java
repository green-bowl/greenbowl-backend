package com.greenbowl.greenbowlserver.fridge.domain.exception;

import javax.persistence.EntityExistsException;

public class DuplicateCategoryException extends EntityExistsException {
    public DuplicateCategoryException(String message) {
        super(message);
    }
}
