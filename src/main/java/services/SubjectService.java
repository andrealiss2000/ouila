package services;

import domain.Subject;
import exceptions.SubjectFormatException;

public interface SubjectService {
	
	Subject validateSubject(String name) throws SubjectFormatException; 
	
	Subject addSubject(String name) throws SubjectFormatException;

}
