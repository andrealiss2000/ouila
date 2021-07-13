package com.lpiot.ouila.repositories;

import com.lpiot.ouila.exceptions.SubjectFormatException;

import com.lpiot.ouila.domain.*;

public interface SubjectRepository {
	
	Integer create(String name) throws SubjectFormatException;
	
	Subject getSubjectByName(String name) throws SubjectFormatException;
	

}
