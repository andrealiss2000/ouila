package repositories;

import java.sql.Blob;
import java.util.List;

import domain.Justification;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface JustificationRepository {

	List<Justification> findAll() throws ResourceNotFoundException;

	Justification findById(int justificationId) throws ResourceNotFoundException;

	int create(int justificationId, String comment, Blob document) throws BadRequestException;

	void update(int justificationId, Justification justification) throws BadRequestException;

	void removeById(int justificationId);
}
