package com.lpiot.ouila.exceptions;

public class LessonNotFoundException extends RuntimeException {
    public LessonNotFoundException(Long id) {
        super("Le cours " + id + " n'existe pas.");
    }
}
