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

	// 추가
	@Override
	public void personInsert(PersonDTO person) {
		String sql = "insert into person values(?, ?, ?, ?, ?)";
		// ?가 여러개라서 Object 객체 배열로
		Object[] param = new Object[] {
				person.getId(), person.getName(), 
				person.getPassword(), person.getGender(), 
				person.getJob() 
			};
		template.update(sql, param); // 실행 : template가 해줌 -> sql문을 실행하는데, 값은 param에 들어있다
	}

	@Override
	public List<PersonDTO> personList() {
		String sql = "select * from person"; // 결과값 있음 -> resultSet의 집합체 : RowMapper
		List<PersonDTO> personlist = template.query(sql, new RowMapper<PersonDTO>() { // select는 결과값이 많을 때 query 사용 -> while문 돌릴필요 없음(알아서 list에 담김)
			@Override
			public PersonDTO mapRow(ResultSet rs, int arg1) throws SQLException { // resultSet 객체를 가진 메서드 구현
				PersonDTO user = new PersonDTO();
				user.setGender(rs.getString("gender"));
				user.setId(rs.getString("id"));
				user.setJob(rs.getString("job"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				return user; // user형에 쌓여서 return됨
			}			
		}); 
		return personlist; // user가 여러개 쌓인 것이 personlist
	}

	@Override
	public PersonDTO personView(String id) {
		String sql = "select * from person where id = '" + id + "'";
		PersonDTO user = template.queryForObject(sql, new RowMapper<PersonDTO>() { // queryForObject는 객체를 받아옴

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

	@Override
	public void personUpdate(PersonDTO person) {
		String sql = "update person set name=?, job=?, gender=?, password=? where id=?"; // ? 가 여러개
		Object[] param = new Object[] {
				person.getName(), person.getJob(), person.getGender(),
				person.getPassword(), person.getId()
		};
		template.update(sql, param);
	}

	@Override
	public void personDelete(String id) {
		String sql = "delete from person where id = '" + id + "'"; // 매개변수, return값 필요없음
		template.update(sql); // 실행만 하면 됨 jdbc template이 connect 등을 다 포함하고 있음
	}

}
