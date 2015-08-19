package kmj.webboard.dao;

import java.util.ArrayList;
import java.util.List;

import kmj.webboard.model.UserVO;

public class UserDao {

    private List<UserVO> users = new ArrayList<UserVO>();

    public UserDao() {
        users.add(new UserVO("james1", "james@naver.com", "1111"));
        users.add(new UserVO("tom", "tom@naver.com", "2222"));
    }

    public List<UserVO> finaAllUser() {
        return users;
    }

    public UserVO insertUser(String userId, String email, String password) throws RuntimeException {
        // 새로운 user를 생성한 후에 users 안에 담아둔 후 userVO를 반환해 봅니다.
        // id하고 이메일이 중복된 경우 예외를 던져줘야 합니다.
        // 1. userid 중복 여부를 검사
        // throw new UserException("id중복입니다.");
        // 2. 이메일 중복 여부를 검사
        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).getUserId().equals(userId) || users.get(i).getEmail().equals(email)) {

                throw new RuntimeException();
            }

        }

        users.add(new UserVO(userId, email, password));

        return users.get(users.size() - 1);

    }

    public UserVO login(String userId, String password) {
        for (int i = 0; i < users.size(); i++) {
            
            if (users.get(i).getUserId().equals(userId) && users.get(i).getPassword().equals(password)) {
                return users.get(i);
            }
        }
        return null;
    }

}
