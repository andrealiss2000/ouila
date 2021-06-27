package services;

import domain.User;
import exceptions.EtAuthException;

public interface UserService {
	User validateUser(String username, String password) throws EtAuthException;
}
