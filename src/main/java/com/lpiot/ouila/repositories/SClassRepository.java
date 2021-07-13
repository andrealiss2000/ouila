package com.lpiot.ouila.repositories;

import java.util.Date;
import java.util.List;

import com.lpiot.ouila.domain.*;

public interface SClassRepository {
	
	Integer addClass(Integer idSubject, Integer idTeacher, Date start, Date end); 
	
	List<SClass> getSClassByUser(Integer idUser);
}
