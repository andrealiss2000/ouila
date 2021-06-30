package services;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.User;
import exceptions.EtAuthException;
import exceptions.EtBadRequestException;
import exceptions.EtRessourceNotFoundException;
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
	@Override
	public List<User> getAllUsers() {
		return userRepository.findUsers();
	}
	
	@Override
	public List<User> getStudents(Integer idClass) {
		return userRepository.findByClass(idClass);
	}
	
	@Override
	public User getStudent(Integer userId) {
		return userRepository.findById(userId);
	}
	
	@Override
	public User addUser(String firstname, String surname, String mail, String username, String password,
			int course, int role) throws EtAuthException {
		Pattern pattern = Pattern.compile("^(.+)@(.+)$");
		if(mail != null) mail = mail.toLowerCase();
		if(!pattern.matcher(mail).matches())
			throw new EtAuthException("Invalid email format");
		Integer count = userRepository.getCountByEmail(mail);
		if(count>0)
			throw new EtAuthException("Email already in use");
		int userId = userRepository.create(firstname, surname, mail, username, password, course, role);
		return userRepository.findById(userId);
	}
	
	@Override
	public User deleteUser(Integer id) throws EtRessourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
