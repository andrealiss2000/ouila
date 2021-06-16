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

	public Justification addJustification(Justification justification) {
		return justificationRepository.save(justification);
	}

	public Justification getJustificationById(Long justificationId) {
		return justificationRepository.findById(justificationId).get();
	}

	public Justification updateJustification(Long id, Justification justification) {
		return justificationRepository.findById(id).map(c -> {
			c.setComment(justification.getComment());
			c.setDocument(justification.getDocument());
			return justificationRepository.save(c);
		}).orElseGet(() -> {
			return justificationRepository.save(justification);
		});
	}

	public void deleteJustificationById(Long justificationId) {
		justificationRepository.deleteById(justificationId);
	}

	public List<Justification> getAllJustifications() {
		return justificationRepository.findAll();
	}
}
