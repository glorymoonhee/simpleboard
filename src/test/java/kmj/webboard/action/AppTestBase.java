package kmj.webboard.action;

import static org.mockito.Mockito.mock;

import org.junit.Before;

import kmj.webboard.dao.IPostDao;
import kmj.webboard.dao.IUserDao;
import kmj.webboard.model.PostVO;
import kmj.webboard.model.UserVO;
import kmj.webboard.util.BoardContext;
import mockservlets.ServletMocker;

public class AppTestBase {
	protected BoardContext boardContext;

	protected ServletMocker mocker;

	protected IUserDao userDao = mock(IUserDao.class);
	protected IPostDao postDao = mock(IPostDao.class);
	
	@Before
	public void beforeClass() {
		mocker = new ServletMocker("");

		mocker.contextAttr(BoardContext.ATTR_USERDAO, userDao);
		mocker.contextAttr(BoardContext.ATTR_POSTDAO, postDao);
		
		boardContext = new BoardContext(mocker.servletContext());
		mocker.contextAttr(BoardContext.ATTR_BOARD_CONTEXT, boardContext);
	}
	protected UserVO anyUser() {
		return new UserVO(1000, "nick", "nick", "e@mail.com", "pass");
	}
	protected PostVO anyPost() {
		return new PostVO(0, "t", "c", "2015-01-01", 0, anyUser());
	}
	
}
