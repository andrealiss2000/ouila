package com.lpiot.ouila.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lpiot.ouila.domain.SClass;
import com.lpiot.ouila.repositories.SClassRepository;
import com.lpiot.ouila.services.SClassService;

@Service
@Transactional
public class SClassServiceImpl implements SClassService {
	
	@Autowired
	SClassRepository sclassRepository;

	@Override
	public Integer addSClass(Integer idSubject, Integer idTeacher, Date start, Date end) {
		Integer classId = sclassRepository.addClass(idSubject, idTeacher, start, end);
		return classId;
	}

	@Override
	public List<SClass> getSClassByUser(Integer idUser) {
		List<SClass> classesList = sclassRepository.getSClassByUser(idUser);
		return classesList;
	}
	
	

}
