package repositories;

import domain.User;
import exceptions.EtAuthException;

public interface UserRepository {
	User findByUsernameAndPassword(String username, String password) throws EtAuthException;
	
}
