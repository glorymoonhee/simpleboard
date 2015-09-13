package kmj.webboard.dao;

import java.util.List;

import kmj.webboard.model.PostVO;
import kmj.webboard.model.UserVO;

public interface IPostDao {

	public abstract PostVO insertPost(UserVO user, String title, String content);

	public abstract List<PostVO> findAll();

	public abstract PostVO findbysiq(String pid);

	public abstract PostVO update(String seq, String title, String content);

	public abstract PostVO delete(String pattern);
	
}