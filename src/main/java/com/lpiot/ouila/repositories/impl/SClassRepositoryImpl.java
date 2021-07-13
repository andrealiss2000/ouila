package com.lpiot.ouila.repositories.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lpiot.ouila.domain.SClass;
import com.lpiot.ouila.domain.Subject;
import com.lpiot.ouila.domain.User;
import com.lpiot.ouila.domain.Role;


import com.lpiot.ouila.exceptions.SubjectFormatException;
import com.lpiot.ouila.repositories.SClassRepository;

@Repository
public class SClassRepositoryImpl implements SClassRepository{
	
	@Autowired 
	JdbcTemplate jdbcTemplate;

	private static final String SQL_CREATE="INSERT INTO sclass(subject, teacher, start, end) VALUES (?,?,?,?)";
	private static final String SQL_GET_CLASS_BY_USER="SELECT subject.id as idSubject, subject.name as nameSubject, "
			+ "role.id	as idRole, role.name as nameRole, teacher.id as idUser, teacher.first_name, teacher.last_name, "
			+ "teacher.email, teacher.username, teacher.password, sclass.id as idClass, sclass.start as startClass, "
			+ "sclass.end as endClass from sclass inner join subject on sclass.subject = subject.id "
			+ "inner join user as teacher on teacher.id = sclass.teacher "
			+ "inner join role on teacher.role = role.id "
			+ "where sclass.teacher = ? ";
	
	/**
	 * Ajouter un cours
	 */
	@Override
	public Integer addClass(Integer idSubject, Integer idTeacher, Date start, Date end) {
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder(); 
			jdbcTemplate.update(connection->{
				PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, idSubject);
				ps.setInt(2, idTeacher);
				ps.setTimestamp(3,  new java.sql.Timestamp(start.getTime()));
				ps.setTimestamp(4, new java.sql.Timestamp(end.getTime()));
					
				return ps;
				
			}, keyHolder);
			return (Integer) keyHolder.getKeys().get("id");
			
		}catch(Exception e) {
			System.out.println(e.getCause());
			throw new SubjectFormatException("Invalid details. Failed to create class :" + e.getMessage()); 
		}
	}

	/**
	 * Permet de récupérer une liste de cours pour un professeur en particulier
	 */
	@Override
	public List<SClass> getSClassByUser(Integer idUser) {
		return jdbcTemplate.query(SQL_GET_CLASS_BY_USER, new Object[] {idUser}, sclassRowMapper);
	}
	
	private RowMapper<SClass> sclassRowMapper = ((rs, rowNum) ->{
		Subject subject = new Subject(rs.getInt("idSubject"), rs.getString("nameSubject"));
		Role role = new Role(rs.getInt("idRole"), rs.getString("nameRole"));
		User user  = new User(rs.getInt("idUser"), rs.getString("first_name"), rs.getString("last_name"),rs.getString("email"), rs.getString("username"), rs.getString("password"), null,role);
		return new SClass(rs.getInt("idClass"), subject, user, rs.getTimestamp("startClass"), rs.getTimestamp("endClass") );
	});

}
