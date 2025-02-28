package com.greenbowl.greenbowlserver.recipe.exception;

import javax.persistence.EntityNotFoundException;

public class RecipeNotFoundException extends EntityNotFoundException {
    public RecipeNotFoundException(String message) {
        super(message);
    }
}
