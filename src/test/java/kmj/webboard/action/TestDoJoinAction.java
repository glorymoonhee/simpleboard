package kmj.webboard.action;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import kmj.webboard.action.DoJoinAction;
import kmj.webboard.action.View;
import kmj.webboard.dao.IUserDao;
import kmj.webboard.model.UserVO;

public class TestDoJoinAction extends AppTestBase{
	
	private IUserDao USER_DAO;
	
	private static UserVO user = new UserVO("james", "james@email", "1111");
	@Before
	public void install(){
		
		USER_DAO = boardContext.getUserDao();
		when ( boardContext.getUserDao() ).thenReturn(USER_DAO);
		
		when( USER_DAO.insertUser(
				anyString(),
				anyString(),
				anyString()) ).thenReturn(user);

		mocker.requestParam("userid", user.getUserId())
		      .requestParam("email", user.getEmail())
		      .requestParam("pass", user.getPassword());
	}
	@Test
	public void test_join() throws IOException, ServletException {
		
		View view = new DoJoinAction().process(boardContext, 
				mocker.httpRequest(), 
				mocker.httpResponse());
		
		verify(USER_DAO, times(1))
			.insertUser(user.getUserId(), user.getEmail(), user.getPassword());
		verify(mocker.httpSession(), times(1)).setAttribute(Mockito.anyString(), any());
		assertThat(view.isRedirect(), is(true));
		assertThat(view.getUri(), is("/success"));
	}

}
