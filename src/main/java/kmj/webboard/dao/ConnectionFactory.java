package kmj.webboard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * database 연결을 생성하는 클래스입니다.
 * 
 * @author Administrator
 *
 */
public class ConnectionFactory {
	String dburl = "";
	String user = "";
	String pass = "";

	public ConnectionFactory() {
	}

	public void setDburi(String dburl) {
		this.dburl = dburl;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * 예외를 내가 직접 처리할 것인지, 아니면 나를 호출한 메소드한테 던져버릴 것인지....?
	 * 
	 * checked exception - xxxxx extends Exception unchecked exception - xxxxx
	 * extends RuntimeException
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(this.dburl, this.user, this.pass);
	}

}
