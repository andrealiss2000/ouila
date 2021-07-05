package com.lpiot.ouila.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Long id) {
        super("La formation " + id + " n'existe pas.");
    }
}
