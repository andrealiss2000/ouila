package services;

import domain.Subject;
import exceptions.SubjectFormatException;

public interface SubjectService {
	
	
	Integer addSubject(String name) throws SubjectFormatException;
	
	

}
