package com.person.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class PersonDAOImpl implements PersonDAO {
	private JdbcTemplate template;

	//setter
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	//추가
	@Override
	public void personInsert(PersonDTO person) {
		String sql = "insert into person values(?,?,?,?,?)";
		Object[] param = new Object[] {
			person.getId(), person.getName(), person.getPassword(), 
			person.getGender(), person.getJob()	
		};
		template.update(sql, param);
	}

	//전체보기
	@Override
	public List<PersonDTO> personList() {
		String sql = "select * from person";
		List<PersonDTO> personlist = template.query(sql, new RowMapper<PersonDTO>() { //여러개 담을때 query 사용
			@Override
			public PersonDTO mapRow(ResultSet rs, int arg1) throws SQLException {
				PersonDTO user = new PersonDTO();
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

	//상세보기
	@Override
	public PersonDTO personView(String id) {
		String sql = "select * from person where id='"+id+"'";
		PersonDTO user = template.queryForObject(sql, new RowMapper<PersonDTO>() {
			@Override
			public PersonDTO mapRow(ResultSet rs, int arg1) throws SQLException {
				PersonDTO person = new PersonDTO();
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

	//업데이트
	@Override
	public void personUpdate(PersonDTO person) {
		String sql = "update person set name=?, job=?, gender=?, password=? where id=?";
		Object[] param = new Object[] {
				person.getName(), person.getJob(), person.getGender(),
				person.getPassword(), person.getId()
		};
		template.update(sql, param);
	}

	//삭제
	@Override
	public void personDelete(String id) {
		String sql = "delete from person where id='"+id+"'";
		template.update(sql);
		
	}

}
