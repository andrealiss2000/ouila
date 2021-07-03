package repositories;

import java.sql.PreparedStatement;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.cj.xdevapi.Statement;

import domain.Course;
import domain.Role;
import domain.User;
import exceptions.EtAuthException;
import exceptions.EtBadRequestException;
import exceptions.EtRessourceNotFoundException;

@Repository
public class UserRepositoryImpl implements UserRepository {

	private static final String SQL_FIND_BY_USERNAME_AND_PASSWORD = "SELECT id, 'firstname', 'lastname', username, email, password, course, role FROM USER WHERE username = ? AND password = ?";
	private static final String SQL_CREATE = "INSERT INTO USER (ID, FIRSTNAME, LASTNAME, EMAIL, USERNAME, PASSWORD, COURSE, ROLE) VALUES(NEXTVAL('USER_SEQ'), ?, ?, ? ,?, ?, ? , ?)";
	private static final String SQL_FIND_ALL = "SELECT id, first_name, last_name, username, password, course FROM USER";
	private static final String SQL_FIND_BY_ID = "SELECT id, first_name, last_name, username, password, course FROM USER WHERE id =?";
	private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM USER WHERE email = ?";
	private static final String SQL_DELETE = "DELETE FROM USER WHERE id = ?";

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public User findByUsernameAndPassword(String username, String password) throws EtAuthException { 
		try { 
			User user = jdbcTemplate.queryForObject(SQL_FIND_BY_USERNAME_AND_PASSWORD, new Object[] { username, password }, userRowMapper);
			 
			return user;
		} catch (EmptyResultDataAccessException e) {
			throw new EtAuthException("Invalid username/password");
		}
	}

	@Override
	public List<User> findUsers() {
		return jdbcTemplate.query(SQL_FIND_ALL, userRowMapper);
	}

	@Override
	public User findById(Integer id) {
		return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[] { id }, userRowMapper);
	}

	@Override
	public List<User> findByClass(Integer idClass) throws EtRessourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer create(String firstname, String lastname, String email, String username, String password, int course,
			int role) throws EtAuthException {
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(SQL_CREATE,
						java.sql.Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, firstname);
				ps.setString(2, lastname);
				ps.setString(3, email);
				ps.setString(4, username);
				ps.setString(5, password);
				ps.setInt(6, course);
				ps.setInt(7, role);
				return ps;
			}, keyHolder);
			return (Integer) keyHolder.getKeys().get("ID");
		} catch (Exception e) {
			throw new EtAuthException("Invalid details. Failed to create account");
		}
	}

	@Override
	public Integer getCountByEmail(String email) {
		return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, new Object[] { email }, Integer.class);
	}

	@Override
	public Integer removeById(Integer userId) {
		return jdbcTemplate.queryForObject(SQL_DELETE, new Object[] { userId }, Integer.class);
	}
 
	
	private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
		return new User(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"),
				rs.getString("username"), rs.getString("password"), rs.getInt("course"), rs.getInt("role"));
	});

}
