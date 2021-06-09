package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Justification;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.JustificationRepository;

@Service
@Transactional
public class JustificationService {

	@Autowired
	JustificationRepository justificationRepository;

	public List<Justification> fetchAllJustifications(Integer userId, Integer categoryId) {
		return null;
	}

	public Justification fetchJustificationById(Integer justificationId) throws ResourceNotFoundException {
		return null;
	}

	public Justification addJustification(Integer userId, Integer categoryId, Double amount, String note,
			Long transactionDate) throws BadRequestException {
		return null;
	}

	public void updateJustification(Integer userId, Integer categoryId, Integer transactionId,
			Justification transaction) throws BadRequestException {
	}

	public void removeJustification(Integer userId, Integer categoryId, Integer transactionId)
			throws ResourceNotFoundException {
	}
}
