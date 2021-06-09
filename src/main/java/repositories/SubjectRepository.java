package repositories;

import domain.Subject;
import exceptions.SubjectFormatException;

public interface SubjectRepository {
	
	Integer create(String name) throws SubjectFormatException;
	
	Subject getSubjectsByName(String name) throws SubjectFormatException;
	
	Subject getSubjectById(int id) throws SubjectFormatException;

}
