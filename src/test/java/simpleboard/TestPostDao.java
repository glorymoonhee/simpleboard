package simpleboard;

import static org.junit.Assert.*;
import kmj.webboard.dao.IPostDao;
import kmj.webboard.dao.MockPostDao;

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
	public void test_insert() {
		IPostDao dao = new MockPostDao();
		dao.insertPost(null, null, null);
		
		assertEquals ( 3, dao.findAll().size());
		assertNotNull ( dao.findbysiq("129992"));
	}

}
