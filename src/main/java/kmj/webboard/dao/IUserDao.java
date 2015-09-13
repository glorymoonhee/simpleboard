package kmj.webboard.dao;

import java.util.List;

import kmj.webboard.model.UserVO;

public interface IUserDao {

	public abstract List<UserVO> finaAllUser();

	public abstract UserVO insertUser(String userId, String email,
			String password) throws RuntimeException;

	public abstract UserVO login(String id, String pass);

	public abstract UserVO findbyuserid(String pid);

	public abstract UserVO findBySeq(Integer seq);
	

}