package com.lpiot.ouila.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("L'utilisateur " + id + " n'existe pas.");
    }
}
