package kmj.webboard.dao;

import static org.junit.Assert.*;
import kmj.webboard.model.PostVO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPostDao {

	private ConnectionFactory fac;
	@Before
	public void setUp() throws Exception {
		fac = new ConnectionFactory();
		fac.setDburi("jdbc:mysql://localhost:3306/sboarddb");
		fac.setDburi("root");
		fac.setDburi("1111");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_findbysiq() {
		DBPostDao dao = new DBPostDao(fac);
		PostVO post = dao.findbysiq("11");
		assertNotNull(post);
	    assertEquals(post.getWriter().getUserId(),"mama");
		
	}
	@Test
	public void test_update() {
		DBPostDao dao = new DBPostDao(fac);
		PostVO post = dao.update("11", "진짜바뀌었니", "바꼇다");
		assertNotNull(post);
	    assertEquals(post.getTitle(),"진짜바뀌었니");
		
	}
	

}
