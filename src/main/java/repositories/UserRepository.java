package repositories;

import java.util.List;

import domain.User;
import exceptions.EtAuthException;
import exceptions.EtBadRequestException;
import exceptions.EtRessourceNotFoundException;

public interface UserRepository {
	User findByUsernameAndPassword(String username, String password) throws EtAuthException;

	List<User> findUsers();

	List<User> findByClass(Integer idClass) throws EtRessourceNotFoundException;

	User findById(Integer userId) throws EtRessourceNotFoundException;
	
	Integer create(String firstname, String surname, String mail, String username, String password, int course, int role) throws EtAuthException;
	
	Integer removeById(Integer userId);

	Integer getCountByEmail(String mail);
}
