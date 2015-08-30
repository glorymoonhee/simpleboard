package kmj.webboard.dao;

import java.util.ArrayList;
import java.util.List;




import kmj.webboard.model.UserVO;

public class UserDao {

	private List<UserVO> users = new ArrayList<UserVO>();
	
	public UserDao() {
		users.add(new UserVO(3200, "제임스", "james1", "james@naver.com", "1111"));
		users.add(new UserVO(3201, "톰", "tom", "tom@naver.com", "2222"));
		users.add(new UserVO(3202, "러블", "lovely", "lovely@naver.com", "2222"));
		users.add(new UserVO(3203, "ㅇㄴㅇㅇ", "g5g5g", "g5g5g@naver.com", "2222"));
		users.add(new UserVO(3204, "ㅎㅎㅎ", "sss", "sss@naver.com", "2222"));
	}

	public List<UserVO> finaAllUser() {
		return users;
	}
	
	public UserVO insertUser ( String userId, String email, String password) throws RuntimeException {
		// 새로운 user를 생성한 후에  users 안에 담아둔 후 userVO를 반환해 봅니다.
		// id하고 이메일이 중복된 경우 예외를 던져줘야 합니다.
		// 1. userid 중복 여부를 검사
		//  throw new UserException("id중복입니다.");
		// 2. 이메일 중복 여부를 검사
		for(int i=0; i < users.size(); i++){
			
			if(users.get(i).getUserId().equals(userId) || users.get(i).getEmail().equals(email)){
		         //아이디 또는 이메일이 중복되면 
	           throw new RuntimeException();
	           }
	        	 
		}
		
			users.add(new UserVO(userId,email,password));    
      		
       		return users.get(users.size()-1);
	
		
	}
	public UserVO login(String id,String pass){

		for(int i=0; i<users.size(); i++){
           UserVO user = users.get(i);
           
           if(user.getUserId().equals(id) && user.getPassword().equals(pass)){
        	   return user;
           }
		}
		
		return null;
	}
	
}

