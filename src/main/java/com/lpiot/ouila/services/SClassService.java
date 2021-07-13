package com.lpiot.ouila.services;

import java.util.Date;
import java.util.List;

import com.lpiot.ouila.domain.SClass;

public interface SClassService {
	
	Integer addSClass(Integer idSubject, Integer idTeacher, Date start, Date end);
	
	List<SClass> getSClassByUser(Integer idUser);
	
	
	
	

}
