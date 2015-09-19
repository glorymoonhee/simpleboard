package kmj.webboard.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kmj.webboard.model.UserVO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestJdbcProgramming {

	@Before
	public void setUp() throws Exception {
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws ClassNotFoundException, SQLException {
		Class.forName("org.mariadb.jdbc.Driver");
		/*
		seq         : INT
		nickName    :  VARCHAR(50)
		userId      :  VARCHAR(50)
		email       :  VARCHAR(50)
		password    :  VARCHAR(50)
		
		create table users (
		  seq Int(11) primary key autoincrement,
		  nickName  varchar(50) not null,
		 
		)
		 */
		String query = "select seq, nickname, userid, email, password from users where seq=?";
		
		String url = "jdbc:mysql://localhost:3306/sboarddb";
		String user = "root";
		String pass = "1111";
		Connection conn = DriverManager.getConnection(url, user, pass);
		///
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, 2);
		
		// db 서버가 쿼리를 실행한 결과가 ResultSet에 담겨 있습니다.
		ResultSet rs = stmt.executeQuery();
		Integer seq = null;
		String nickName = null;
		String userId = null;
		String email = null;
		String password = null;
		
		if ( rs.next()) {
			seq = rs.getInt("seq");
			nickName = rs.getString("nickname");
			userId = rs.getString("userid");
			email = rs.getString("email");
			password = rs.getString("password");
		}
		
		UserVO haha = new UserVO(seq, nickName, userId, email, password);
		assertEquals ("하하하", haha.getNickName());
		///
		rs.close();
		stmt.close();
		conn.close();
	}

}
