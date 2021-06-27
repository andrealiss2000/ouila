package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.User;
import exceptions.EtAuthException;
import repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired 
	UserRepository userRepository;
	@Override
	public User validateUser(String username, String password) throws EtAuthException {
		if(username != null) username = username.toLowerCase();
		return userRepository.findByUsernameAndPassword(username, password);
	}

}
