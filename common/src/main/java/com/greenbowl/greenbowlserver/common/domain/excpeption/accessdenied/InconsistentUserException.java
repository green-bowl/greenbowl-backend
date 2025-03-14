package com.greenbowl.greenbowlserver.common.domain.excpeption.accessdenied;


import org.springframework.security.access.AccessDeniedException;

public class InconsistentUserException extends AccessDeniedException {
    public InconsistentUserException(String message) {
        super(message);
    }
}
