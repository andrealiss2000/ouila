package com.lpiot.ouila.services;

import java.util.List;

import com.lpiot.ouila.domain.Justification;
import com.lpiot.ouila.repositories.JustificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JustificationService {

	@Autowired
	JustificationRepository justificationRepository;

	Justification addCourse(Justification justification) {
		return justificationRepository.save(justification);
	}

	Justification getJustificationById(Long justificationId) {
		return justificationRepository.findById(justificationId).get();
	}

	void updateJustification(Justification justification) {
		Justification newJustification = justificationRepository.findById(justification.getId()).orElseThrow();
		justificationRepository.save(newJustification);
	}

	void deleteJustificationById(Long justificationId) {
		justificationRepository.deleteById(justificationId);
	}

	List<Justification> getAllJustifications() {
		return justificationRepository.findAll();
	}
}
