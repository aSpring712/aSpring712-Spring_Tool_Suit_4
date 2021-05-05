package com.person2.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class Person2DAOImpl implements Person2DAO {
	@Autowired
	private JdbcTemplate template;

	@Override
	public void person_insert(PersonVO person) {
		String sql = "insert into person values(?, ?, ?, ?, ?)";
		Object[] param = new Object[] {
			person.getId(), person.getName(), person.getPassword(),
			person.getGender(), person.getJob()
		};
		template.update(sql, param);
	}

	@Override
	public List<PersonVO> person_list() {
		String sql = "select * from person";
		List<PersonVO> personlist = template.query(sql, new RowMapper<PersonVO>() {
			@Override
			public PersonVO mapRow(ResultSet rs, int arg1) throws SQLException {
				PersonVO user = new PersonVO();
				user.setGender(rs.getString("gender"));
				user.setId(rs.getString("id"));
				user.setJob(rs.getString("job"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		});
		
		return personlist;
	}

	@Override
	public PersonVO person_view(String id) {
		String sql = "select * from person where id = '" + id + "'";
		PersonVO user = template.queryForObject(sql, new RowMapper<PersonVO>() {

			@Override
			public PersonVO mapRow(ResultSet rs, int arg1) throws SQLException {
				PersonVO person = new PersonVO();
				person.setGender(rs.getString("gender"));
				person.setId(rs.getString("id"));
				person.setJob(rs.getString("job"));
				person.setPassword(rs.getString("password"));
				person.setName(rs.getString("name"));
				
				return person;
			}
		});
		return user;
	}

	@Override
	public void person_update(PersonVO person) {
		String sql = "update person set name=?, job=?, gender=?, password=? where id=?";
		Object[] param = new Object[] {
				person.getName(), person.getJob(), person.getGender(),
				person.getPassword(), person.getId()
		};
		template.update(sql, param);
	}

	@Override
	public void person_delete(String id) {
		String sql = "delete from person where id = '" + id + "'";
		template.update(sql);
	}

}
