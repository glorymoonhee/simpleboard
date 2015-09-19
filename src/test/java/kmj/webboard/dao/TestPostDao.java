package kmj.webboard.dao;

import static org.junit.Assert.*;
import kmj.webboard.model.PostVO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPostDao {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_findbysiq() {
		DBPostDao dao = new DBPostDao();
		PostVO post = dao.findbysiq("11");
		assertNotNull(post);
	    assertEquals(post.getWriter().getUserId(),"mama");
		
	}
	@Test
	public void test_update() {
		DBPostDao dao = new DBPostDao();
		PostVO post = dao.update("11", "진짜바뀌었니", "바꼇다");
		assertNotNull(post);
	    assertEquals(post.getTitle(),"진짜바뀌었니");
		
	}
	

}
