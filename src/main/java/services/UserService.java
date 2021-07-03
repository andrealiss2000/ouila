package services;

import java.util.List;

import org.springframework.stereotype.Service;

import domain.User;
import exceptions.EtAuthException;
import exceptions.EtBadRequestException;
import exceptions.EtRessourceNotFoundException;

@Service
public interface UserService {
	User validateUser(String username, String password) throws EtAuthException;
	
	User addUser(String firstname, String lastname, String mail, String username, String password, int course, int role) throws EtAuthException;

	User deleteUser(Integer id) throws EtRessourceNotFoundException;
	
	List<User> getAllUsers();
	 
	User getStudent(Integer userId) throws EtRessourceNotFoundException;

	List<User> getStudents(Integer idClass) throws EtRessourceNotFoundException;
}
