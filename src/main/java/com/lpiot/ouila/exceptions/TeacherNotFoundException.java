package com.lpiot.ouila.exceptions;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(Long id) {
        super("Aucun professeur avec l'id: " + id + " n'a été trouvé.");
    }
}
