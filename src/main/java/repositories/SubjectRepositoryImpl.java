package repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import domain.Subject;
import exceptions.SubjectFormatException;


@Repository
public class SubjectRepositoryImpl implements SubjectRepository{
	
	private static final String SQL_CREATE = "INSERT INTO subject(name) VALUES(?)";
	private static final String SQL_FIND_BY_NAME = "SELECT * FROM subject WHERE name = ? ";
	
	@Autowired 
	JdbcTemplate jdbcTemplate;

	public Integer create(String name) throws SubjectFormatException {
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder(); 
			jdbcTemplate.update(connection->{
				PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, name);
				return ps;
				
			}, keyHolder);
			return (Integer) keyHolder.getKeys().get("id");
			
		}catch(Exception e) {
			throw new SubjectFormatException("Invalid details. Failed to create subject"); 
		}
	}

	public Subject getSubjectByName(String name) throws SubjectFormatException {
		return jdbcTemplate.queryForObject(SQL_FIND_BY_NAME, new Object[] {name}, subjectRowMapper);
	}
	

	
	private RowMapper<Subject> subjectRowMapper = ((rs, rowNum) ->{
		return new Subject(rs.getInt("id"),
				rs.getString("name"));
	});

}
