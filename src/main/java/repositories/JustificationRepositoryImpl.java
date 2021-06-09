package repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import domain.Justification;
import exceptions.ResourceNotFoundException;

public class JustificationRepositoryImpl implements JustificationRepository {

	private static final String SQL_FIND_BY_ID = "SELECT * FROM justifications WHERE id = ?";
	private static final String SQL_FIND_ALL = "SELECT * FROM justifications;";

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Justification> findById(int justificationId) throws ResourceNotFoundException {
		return jdbcTemplate.query(SQL_FIND_BY_ID, new Object[] { justificationId }, justificationRowMapper);
	}

	@Override
	public List<Justification> findAll() throws ResourceNotFoundException {
		return jdbcTemplate.query(SQL_FIND_ALL, justificationRowMapper);
	}

	private RowMapper<Justification> justificationRowMapper = ((rs, rowNum) -> {
		return new Justification(rs.getInt("id"), rs.getString("comment"), rs.getBlob("document"));
	});

}
