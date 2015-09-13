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

public class DBPostDao implements IPostDao {
	private String url = "jdbc:mysql://localhost:3306/sboarddb";
	private String user = "root";
	private String pass = "1111";

	@Override
	public PostVO insertPost(UserVO uservo, String title, String content) throws RuntimeException{
		// FIXME INSERT 작성 안됐음.
		
		return null;
	}


	@Override
	public List<PostVO> findAll() {
		String query = "select seq,title,content,creationtime,viewcount,writer from posts";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<PostVO> list = new ArrayList<PostVO>();
		try {
			conn = DriverManager.getConnection(url, user, pass);
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			
			Integer seq = null;
			String title = null;
			String content = null;
			String creationtime = null;
			Integer viewcount = null;
			String writer = null;
			
			while ( rs.next()) {
				seq = rs.getInt("seq");
				title = rs.getString("title");
				content = rs.getString("content");
				creationtime = rs.getString("creationtime");
				viewcount = rs.getInt("viewcount");
				writer = rs.getString("writer");
			
				
				UserVO u_writer = new UserVO(writer,"dd@naver.com","ddd");
				
				/////////////////////여기부분 고쳐야함.
				PostVO post = new PostVO(seq, title, content, creationtime, viewcount, u_writer);
				list.add(post);
				
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException( e) ;
		} finally {
			close(conn, stmt, rs);
		} 
	}
	
	@Override
	public PostVO readPost( Integer postSeq) {
		// FIXME 조회수를 올려줘야 합니다.
		PostVO post = findbysiq("" + postSeq.intValue());
		Integer vcount = post.getViewCount();
		String query = "update posts set viewcount = ? where seq = ?"; // ?vcount + 1   ?postSeq
		
		// update set viewcount = ? 
		return post;
	}
	/**
	 * userid 로 사용자를 찾기 
	 */
	@Override
	public PostVO findbysiq(String pid) {
		String query = "select seq,title,content,creationtime,viewcount,writer from posts where seq = ?";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(url, user, pass);
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(pid));
			rs = stmt.executeQuery();

			Integer seq = null;
			String title = null;
			String content = null;
			String creationtime = null;
			Integer viewcount = null;
			Integer writerSeq = null;
			PostVO post = null;

			if (rs.next()) {
				seq = rs.getInt("seq");
				title = rs.getString("title");
				content = rs.getString("content");
				creationtime = rs.getString("creationtime");
				viewcount = rs.getInt("viewcount");
				writerSeq = rs.getInt("writer");
				System.out.println(writerSeq);

				DBUserDao userdao = new DBUserDao();
				UserVO u_writer = userdao.findBySeq(writerSeq);
				// ///////////////////여기부분 고쳐야함.
				post = new PostVO(seq, title, content, creationtime, viewcount,
						u_writer);

			}
			return post;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(conn, stmt, rs);
		}
	}
	

	@Override
	public PostVO update(String seq, String title, String content) {
		PostVO post = findbysiq(seq);
		
		if(post!=null){
			String query = "update posts set title=?,content=? where seq=?";
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			
			try {
				conn = DriverManager.getConnection(url, user, pass);
				conn.setAutoCommit(false); // !!!!!
				
				stmt = conn.prepareStatement(query);

				post.setTitle(title);
				post.setContent(content);
			

				stmt.setString(1, post.getTitle());
	            stmt.setString(2, post.getContent());
	            stmt.setInt(3, post.getSeq());
	            
				int cnt = stmt.executeUpdate();
				if ( cnt != 1) {
					// 
					throw new RuntimeException("실패했다");
				}
				
				conn.commit(); // !!!!!!
				
				return post;
				
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				throw new RuntimeException( e ) ;
			} finally {
				close(conn, stmt, rs);
			}
			
			
			
		}
		return null;
	}

	@Override
	public PostVO delete(String seq) {
		

		PostVO post = findbysiq(seq);
		
		if(post!=null){
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				
				String query = "delete from posts where seq = ?";
				conn = DriverManager.getConnection(url, user, pass);
				conn.setAutoCommit(false); // !!!!!
				
				stmt = conn.prepareStatement(query);

				
			

				stmt.setInt(1, Integer.parseInt(seq));
	            
				int cnt = stmt.executeUpdate();
				if ( cnt != 1) {
					// 
					throw new RuntimeException("실패했다");
				}
				
				conn.commit(); // !!!!!!
		    
				
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				throw new RuntimeException( e ) ;
			} finally {
				close(conn, stmt, rs);
			}
			
		}
		return null;
		
	}
	
	
	
	private void close( Connection conn, PreparedStatement stmt, ResultSet rs) {
		try {
			if( rs != null) rs.close();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		try {
			if( stmt != null ) stmt.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if ( conn != null ) conn.close();
		} catch (SQLException e) {}
	}

}
