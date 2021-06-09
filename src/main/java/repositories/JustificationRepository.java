package repositories;

import java.util.List;

import domain.Justification;
import exceptions.ResourceNotFoundException;

public interface JustificationRepository {
	
	List<Justification> findAll() throws ResourceNotFoundException;
	
	List<Justification> findById(int justificationId) throws ResourceNotFoundException;

}
