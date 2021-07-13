package com.lpiot.ouila.services.impl;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lpiot.ouila.exceptions.SubjectFormatException;
import com.lpiot.ouila.repositories.SubjectRepository;
import com.lpiot.ouila.services.SubjectService;
import com.lpiot.ouila.domain.*;


@Service
@Transactional
public class SubjectServiceImpl implements SubjectService{
	
	@Autowired
	SubjectRepository subjectRepository;

	public Subject validateSubject(String name) throws SubjectFormatException {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer addSubject(String name) throws SubjectFormatException {
		if(name!= null) {
			 String firstLetter = name.substring(0, 1);
			 String remainingLetters = name.substring(1, name.length());
			 firstLetter = firstLetter.toUpperCase();
			 name = firstLetter + remainingLetters;
		}else throw new SubjectFormatException("No subject name");
		Integer subjectId = subjectRepository.create(name); 
		return subjectId;
		
	}

}
