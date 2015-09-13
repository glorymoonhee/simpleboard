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
public class MockPostDao implements IPostDao {
	private static int count = 1000;
	List<PostVO> posts = new ArrayList<PostVO>();
	
	public MockPostDao(  ){
//		UserDao userDao = repo.getUserDao();
//		userDao.findById("james1");
		UserVO mockUser = new UserVO("james1", "james@naver.com", "1111");
		mockUser.setSeq(3200);
		System.out.println("뭐냐????????????????");
		posts.add(new PostVO(count++, "demo", "first content", "2015-08-22 12:11:09", 11, mockUser));
		posts.add(new PostVO(count++, "second", "second content", "2015-08-22 13:11:09", 123, mockUser));
	}
	
	/* (non-Javadoc)
	 * @see kmj.webboard.dao.IPostDao#insertPost(kmj.webboard.model.UserVO, java.lang.String, java.lang.String)
	 */
	@Override
	public PostVO insertPost(UserVO user, String title, String content) {
		PostVO newPost = new PostVO(title, content, currentTime(), user);
		newPost.setSeq(count++);
		
	    posts.add(newPost);
		
		return newPost;
	}
	
	private String currentTime() {
		return "2015-08-22 12:33:21";
	}

	/* (non-Javadoc)
	 * @see kmj.webboard.dao.IPostDao#findAll()
	 */
	@Override
	public List<PostVO> findAll() {
		 return posts;
		
	}

	/* (non-Javadoc)
	 * @see kmj.webboard.dao.IPostDao#findbysiq(java.lang.String)
	 */
	@Override
	public PostVO findbysiq(String pid) {
		// SELECT seq, title, content, ...... from users WHERE	seq = ? 
		int seqnum = Integer.parseInt(pid);
		for( PostVO p : posts) {
			if(p.getSeq().intValue() == seqnum ){ 
				return p;
			}
		}
		return null;
		
	}

	/* (non-Javadoc)
	 * @see kmj.webboard.dao.IPostDao#update(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public PostVO update(String seq, String title, String content) {
		 PostVO post = findbysiq(seq);
		 post.setTitle(title);
		 post.setContent(content);
		 // UPDATE SET title = ? , content = ? WHERE seq = ?
		 findbysiq(seq);
		
		 return post;
		 
	}
	
	/* (non-Javadoc)
	 * @see kmj.webboard.dao.IPostDao#delete(java.lang.String)
	 */
	@Override
	public PostVO delete ( String seq) {
	      int seqnum = Integer.parseInt(seq);
	      for(PostVO p : posts){
	    	  if(p.getSeq().intValue() == seqnum){
	    		  posts.remove(p);
	    	  } 
	      }
	      return null;
		 
	}

	
}
