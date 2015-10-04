package kmj.webboard.dao;

import static org.junit.Assert.*;

import java.util.List;

import kmj.webboard.model.UserVO;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TestUserDbDao {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_findAll() {
		DBUserDao userDao = new DBUserDao();
		List<UserVO> users = userDao.finaAllUser();
		assertTrue ( users.size() > 0);
		
	}
	
	@Test
	public void test_login(){
		DBUserDao userDao = new DBUserDao();
		UserVO user = userDao.login("mama", "1111");
		assertNotNull(user);
		
	}
	@Ignore
	@Test
	public void test_insert(){
		DBUserDao userDao = new DBUserDao();
		UserVO user = userDao.insertUser("kakaka", "dd@naver.com", "1111");
		assertNotNull(user);
	}
	
	@Test
	public void test_finebyuserid(){
		DBUserDao userDao = new DBUserDao();
		UserVO user = null;
		String userid = "mama";
		user = userDao.findbyuserid(userid);
		assertNotNull(user);
		assertEquals(user.getUserId(),"mama");
		
	
	}

	
     
}
