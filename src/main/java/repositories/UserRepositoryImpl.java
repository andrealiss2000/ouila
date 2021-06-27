package repositories;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import domain.Course;
import domain.Role;
import domain.User;
import exceptions.EtAuthException;

public class UserRepositoryImpl implements UserRepository {

	private static final String SQL_FIND_BY_USERNAME  = "SELECT id, first_name, last_name, username, password, course,  FROM USER WHERE username =?";
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public User findByUsernameAndPassword(String username, String password) throws EtAuthException {
		try {
			User user = jdbcTemplate.queryForObject(SQL_FIND_BY_USERNAME, new Object[] {username}, userRowMapper);
			if(!BCrypt.checkpw(password, user.getPassword()))
				throw new EtAuthException("Invalid username/password");
			return user;
		}catch(EmptyResultDataAccessException e) {
			throw new EtAuthException("Invalid username/password");
		}
	}
	
	private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
		return new User(rs.getInt("id"),
				rs.getString("firstname"),
				rs.getString("surname"),
				rs.getString("mail"),
				rs.getString("username"),
				rs.getString("password"),
				rs.getInt("course"),
				rs.getInt("role"));
	}); 
}
