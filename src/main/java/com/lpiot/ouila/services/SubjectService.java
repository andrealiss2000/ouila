package com.lpiot.ouila.services;

import com.lpiot.ouila.exceptions.SubjectFormatException;

import com.lpiot.ouila.domain.*;

public interface SubjectService {
	
	
	Integer addSubject(String name) throws SubjectFormatException;
	
	

}
