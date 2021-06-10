package repositories;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import domain.Justification;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public class JustificationRepositoryImpl implements JustificationRepository {

	private static final String SQL_FIND_BY_ID = "SELECT * FROM justifications WHERE id = ?";
	private static final String SQL_FIND_ALL = "SELECT * FROM justifications;";
	private static final String SQL_CREATE = "INSERT INTO justifications (id, comment, document) VALUES(?, ?, ?)";
	private static final String SQL_DELETE = "DELETE FROM justifications WHERE id = ?";
	private static final String SQL_UPDATE = "UPDATE justifications SET comment = ?, document = ? WHERE id = ?";

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Justification findById(int justificationId) throws ResourceNotFoundException {
		return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[] { justificationId },
				new int[] { java.sql.Types.INTEGER }, Justification.class);
	}

	@Override
	public List<Justification> findAll() throws ResourceNotFoundException {
		return jdbcTemplate.query(SQL_FIND_ALL, justificationRowMapper);
	}

	private RowMapper<Justification> justificationRowMapper = ((rs, rowNum) -> {
		return new Justification(rs.getInt("id"), rs.getString("comment"), rs.getBlob("document"));
	});

	@Override
	public int create(int justificationId, String comment, Blob document) throws BadRequestException {
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, justificationId);
				ps.setString(2, comment);
				ps.setBlob(3, document);
				return ps;
			}, keyHolder);
			return (Integer) keyHolder.getKeys().get("id");
		} catch (Exception e) {
			throw new BadRequestException("Invalid request");
		}
	}

	@Override
	public void update(int justificationId, Justification justification) throws BadRequestException {
		try {
			jdbcTemplate.update(SQL_UPDATE,
					new Object[] { justification.getComment(), justification.getDocument(), justificationId });
		} catch (Exception e) {
			throw new BadRequestException("Invalid request");
		}
	}

	@Override
	public void removeById(int justificationId) {
		jdbcTemplate.update(SQL_DELETE, new Object[] { justificationId });
	}

}
