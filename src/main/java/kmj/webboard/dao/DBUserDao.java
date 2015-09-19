package kmj.webboard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kmj.webboard.model.PostVO;
import kmj.webboard.model.UserVO;

public class DBUserDao implements IUserDao {
	// 상수 정보
	private String url = "jdbc:mysql://localhost:3306/sboarddb";
	private String user = "root";
	private String pass = "1111";

	public DBUserDao() {
	}

	@Override
	public List<UserVO> finaAllUser() {
		String query = "select seq, nickname, userid, email, password from users";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		ArrayList<UserVO> list = new ArrayList<UserVO>();
		try {
			conn = DriverManager.getConnection(url, user, pass);
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			Integer seq = null;
			String nickName = null;
			String userId = null;
			String email = null;
			String password = null;

			while (rs.next()) {
				seq = rs.getInt("seq");
				nickName = rs.getString("nickname");
				userId = rs.getString("userid");
				email = rs.getString("email");
				password = rs.getString("password");

				UserVO user = new UserVO(seq, nickName, userId, email, password);
				list.add(user);

			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(conn, stmt, rs);
		}
	}

	@Override
	public UserVO findBySeq(Integer seq) {
		String query = "select seq,nickname,userid,email,password from users where seq = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(url, user, pass);
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, seq.intValue());
			rs = stmt.executeQuery();

			//Integer seq = null;
			String nickname = null;
			String userid = null;
			String email = null;
			String password = null;
			UserVO user = null;

			if (rs.next()) {
				seq = rs.getInt("seq");
				nickname = rs.getString("nickname");
				userid = rs.getString("userid");
				email = rs.getString("email");
				password = rs.getString("password");

				// ///////////////////여기부분 고쳐야함.
				user = new UserVO(seq, nickname, userid, email, password);

			}
			return user;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(conn, stmt, rs);
		}
	}
	/**
	 * MyBatis
	 */

	@Override
	public UserVO findbyuserid(String pid) {
		String query = "select seq,nickname,userid,email,password from users where userid = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(url, user, pass);
			stmt = conn.prepareStatement(query);
			stmt.setString(1, pid);
			rs = stmt.executeQuery();

			Integer seq = null;
			String nickname = null;
			String userid = null;
			String email = null;
			String password = null;
			UserVO user = null;

			if (rs.next()) {
				seq = rs.getInt("seq");
				nickname = rs.getString("nickname");
				userid = rs.getString("userid");
				email = rs.getString("email");
				password = rs.getString("password");

				// ///////////////////여기부분 고쳐야함.
				user = new UserVO(seq, nickname, userid, email, password);

			}
			return user;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(conn, stmt, rs);
		}
	}

	@Override
	public UserVO login(String id, String passw) {
		String query = "select seq, nickname, userid, email, password from users where userid=? and password=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(url, user, pass);
			stmt = conn.prepareStatement(query);

			stmt.setString(1, id);
			stmt.setString(2, passw);
			rs = stmt.executeQuery();

			Integer seq = null;
			String nickName = null;
			String userId = null;
			String email = null;
			String password = null;

			if (rs.next()) {
				seq = rs.getInt("seq");
				nickName = rs.getString("nickname");
				userId = rs.getString("userid");
				email = rs.getString("email");
				password = rs.getString("password");

				UserVO user = new UserVO(seq, nickName, userId, email, password);
				return user;
			}
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(conn, stmt, rs);
		}
	}

	@Override
	public UserVO insertUser(String userId, String email, String password)
			throws RuntimeException {
		String query = "insert into users (nickname,userid,email,password) values (?,?,?,?);";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false); // !!!!!

			stmt = conn
					.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			UserVO newUser = new UserVO(userId, email, password);
			newUser.setNickName(userId);

			stmt.setString(1, newUser.getNickName());
			stmt.setString(2, newUser.getUserId());
			stmt.setString(3, newUser.getEmail());
			stmt.setString(4, newUser.getPassword());

			int cnt = stmt.executeUpdate();
			if (cnt != 1) {
				//
				throw new RuntimeException("실패했다");
			}

			rs = stmt.getGeneratedKeys(); // 서버쪽에서 insert를 할때 생서오딘 PK들을 담아서 보내줌.
			rs.next();
			Integer seq = rs.getInt(1);
			newUser.setSeq(seq);
			conn.commit(); // !!!!!!

			return newUser;

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(conn, stmt, rs);
		}
	}

	private void close(Connection conn, PreparedStatement stmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
		}
	}
}
