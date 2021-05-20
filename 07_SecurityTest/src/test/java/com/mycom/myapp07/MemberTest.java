package com.mycom.myapp07;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ // 아래 두개를 참고해서 실행하라
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/security-context.xml" })
public class MemberTest { // 단위테스트
	@Autowired // type으로 찾음
	private PasswordEncoder pwencoder; // BCryp .. 의 상위를 가져다 써도 됨

	@Autowired
	private DataSource dataSource;

	@Test
	public void testinsertMethod() {
		String sql = "insert into tbl_member(userid, userpw, username) values(?, ?, ?)";
		for (int i = 0; i < 100; i++) {
			Connection con = null;
			PreparedStatement ps = null;

			try {
				con = dataSource.getConnection();
				ps = con.prepareStatement(sql);

				ps.setString(2, pwencoder.encode("pw" + i)); // 2번째 인자 -> pw
				if (i < 80) {
					ps.setString(1, "user" + i); // user0, 1, 2, 3 ..
					ps.setString(3, "일반사용자" + i); // 일반사용자0, 1, 2, ..
				} else if (i < 90) {
					ps.setString(1, "manager" + i);
					ps.setString(3, "운영자" + i);
				} else {
					ps.setString(1, "admin" + i);
					ps.setString(3, "관리자" + i);
				}
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (ps != null) { try { ps.close(); } catch (SQLException e) {	e.printStackTrace(); } }
				if (con != null) { try { con.close(); } catch (SQLException e) { e.printStackTrace(); } }
			} // fianlly
		} // for
	} // testinsertMethod

	@Test
	public void testInsertAuth() {
		String sql = "insert into tbl_member_auth(userid, auth) values(?, ?)";
		for (int i = 0; i < 100; i++) {
			Connection con = null;
			PreparedStatement ps = null;

			try {
				con = dataSource.getConnection();
				ps = con.prepareStatement(sql);

				ps.setString(2, pwencoder.encode("pw" + i)); // 2번째 인자 -> pw
				if (i < 80) {
					ps.setString(1, "user" + i); // user0, 1, 2, 3 ..
					ps.setString(2, "ROLE_USER"); // 일반사용자0, 1, 2, ..
				} else if (i < 90) {
					ps.setString(1, "manager" + i);
					ps.setString(2, "ROLE_MANAGER");
				} else {
					ps.setString(1, "admin" + i);
					ps.setString(2, "ROLE_ADMIN");
				}
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (ps != null) { try { ps.close(); } catch (SQLException e) {	e.printStackTrace(); } }
				if (con != null) { try { con.close(); } catch (SQLException e) { e.printStackTrace(); } }
			} // fianlly
		} // for
	}
	
} // class
