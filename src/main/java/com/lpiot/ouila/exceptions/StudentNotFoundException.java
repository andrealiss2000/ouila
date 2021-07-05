package com.lpiot.ouila.exceptions;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(Long id) {
        super("Aucun étudiant avec l'id: " + id + " n'a été trouvé.");
    }
}
