package com.lpiot.ouila.exceptions;

public class PresenceNotFoundException extends RuntimeException {
    public PresenceNotFoundException(Long id) {
        super("La présence " + id + " n'existe pas.");
    }
}
