package repositories;

import domain.Subject;
import exceptions.SubjectFormatException;

public interface SubjectRepository {
	
	Integer create(String name) throws SubjectFormatException;
	
	Subject getSubjectByName(String name) throws SubjectFormatException;
	

}
