package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDao {

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// < ID / PW >
	private String id = "phonedb";
	private String pw = "phonedb";

	// 생성자

	// 메소드 GS : 현재 필드가 없어서 생성X

	// 매소드-일반

	// --DB연결 메소드
	private void getConnection() {

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// --자원정리 메소드
	private void close() {
		// 5. 자원정리
		try {

			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// --등록 메소드
	public int personInsert(PersonVo pv) throws Exception {
		int count = -1;

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "phonedb", "phonedb");

			getConnection();

			String query = "";
			query += " insert into person";
			query += " values(seq_person_id.nextval, ?, ?, ?)";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pv.getName());
			pstmt.setString(2, pv.getHp());
			pstmt.setString(3, pv.getCompany());

			count = pstmt.executeUpdate();

			System.out.println(count + "건이 등록되었습니다.");

		} catch (SQLException e) {
			System.out.println("error: " + e);
		}

		close();
		return count;
	}

	// 업데이트
	public int personUpdate(PersonVo pv) throws Exception {
		int count = -1;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "phoneb", "phonedb");

			getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " update person";
			query += " set	  name = ?,";
			query += " 		  hp = ?,";
			query += " 		  company = ?";
			query += " where person_id = ?";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pv.getName());
			pstmt.setString(2, pv.getHp());
			pstmt.setString(3, pv.getCompany());
			pstmt.setInt(4, pv.getPersonId());

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 수정되었습니다.");

		} catch (SQLException e) {
			System.out.println("error: " + e);
		}

		close();
		return count;
	}

	// 수정
	public int personUpdate1(PersonVo pv) {
		int count = -1;

		try {
			getConnection();

			String query = "";
			query += " update person";
			query += " set	  name = ?,";
			query += " 		  hp = ?,";
			query += " 		  company = ?";
			query += " where person_id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pv.getName());
			pstmt.setString(2, pv.getHp());
			pstmt.setString(3, pv.getCompany());
			pstmt.setInt(4, pv.getPersonId());

			count = pstmt.executeUpdate();

			System.out.println(count + "건이 수정되었습니다.");

		} catch (SQLException e) {
			System.out.println("error: " + e);
		}

		close();
		return count;
	}

	// 삭제
	public int personDelete(int personId) throws Exception {
		int count = -1;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "phonedb", "phonedb");

			getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " delete from person";
			query += " where person_id = ?";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, personId);

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 삭제되었습니다.");

		} catch (SQLException e) {
			System.out.println("error: " + e);
		}

		close();
		return count;
	}

	// 리스트 select
	public List<PersonVo> phoneSelect() {

		List<PersonVo> phoneList = new ArrayList<PersonVo>();

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "phonedb", "phonedb");

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " select person_id ";
			query += "        , Name ";
			query += "        , Hp ";
			query += "        , company ";
			query += " from person ";

			// 바인딩
			pstmt = conn.prepareStatement(query);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리

			// 반복문으로 Vo 만들기 List에 추가하기
			while (rs.next()) {

				int personId = rs.getInt("person_id");
				String name = rs.getString("Name");
				String hp = rs.getString("Hp");
				String company = rs.getString("company");

				PersonVo personVo = new PersonVo(personId, name, hp, company);

				phoneList.add(personVo);

			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {

				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}

		return phoneList;
	}

}
