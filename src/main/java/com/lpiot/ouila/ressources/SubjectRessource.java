package com.lpiot.ouila.ressources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lpiot.ouila.domain.*;
import com.lpiot.ouila.services.SubjectService;

@RestController
@RequestMapping("/")
public class SubjectRessource {
	
	@Autowired
	SubjectService subjectService;
	
	
	@PostMapping("/subjects")
	public ResponseEntity<Map<String, String>> addSubject(@RequestBody Map<String, Object> classMap) {
		
		
		List<String> subjetList = (ArrayList<String>) classMap.get("subjectList");
		Map<String,String> map = new HashMap<>();
		if(subjetList!= null) {
			for(String subject : subjetList) {
				Integer subjectId = subjectService.addSubject(subject);
				map.put("status", "subject added successfully");
				map.put(subject, subject);
			}
		}
		
	
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	

}
