package kmj.webboard.dao;

import java.util.ArrayList;
import java.util.List;

import kmj.webboard.model.PostVO;
import kmj.webboard.model.UserVO;
/**
 * DaoRepository - userDao, postDao
 * @author Administrator
 *
 */
public class PostDao {
	private static int count = 1000;
	List<PostVO> posts = new ArrayList<PostVO>();
	
	public PostDao(  ){
//		UserDao userDao = repo.getUserDao();
//		userDao.findById("james1");
		UserVO mockUser = new UserVO("james1", "james@naver.com", "1111");
		mockUser.setSeq(3200);
		System.out.println("뭐냐????????????????");
		posts.add(new PostVO(count++, "demo", "first content", "2015-08-22 12:11:09", 11, mockUser));
		posts.add(new PostVO(count++, "second", "second content", "2015-08-22 13:11:09", 123, mockUser));
	}
	
	public PostVO insertPost(UserVO user, String title, String content) {
		PostVO newPost = new PostVO(title, content, currentTime(), user);
		newPost.setSeq(count++);
		
	    posts.add(newPost);
		
		return newPost;
	}
	
	private String currentTime() {
		return "2015-08-22 12:33:21";
	}

	public List<PostVO> findAll() {
		 return posts;
		
	}

	public PostVO findbysiq(String pid) {
		int seqnum = Integer.parseInt(pid);
		for( PostVO p : posts) {
			if(p.getSeq().intValue() == seqnum ){ 
				return p;
			}
		}
		return null;
		
	}

	
}
