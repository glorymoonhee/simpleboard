package kmj.webboard.action.page;

import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import kmj.webboard.action.AppTestBase;
import kmj.webboard.action.View;
import kmj.webboard.action.page.PageJoinAction;
import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.ServletException;

public class TestPageJoinAction extends AppTestBase {

	

	@Before
	public void install() {
	}
	
	@Test
	public void when_loginInfo_exists_in_session() throws IOException, ServletException {
		mocker.useSession();
		PageJoinAction action = new PageJoinAction();
		View view = action.process(boardContext, mocker.httpRequest(), mocker.httpResponse());
		
		verify(mocker.httpSession(), times(1)).invalidate();
		assertThat(view.isFowward(), CoreMatchers.is(true));
		
	}
	
	@Test
	public void no_logingInfo_in_the_session() throws IOException, ServletException {
		PageJoinAction action = new PageJoinAction();
		View view = action.process(boardContext, mocker.httpRequest(), mocker.httpResponse());
		
		verify(mocker.httpRequest(), never()).setAttribute(anyString(), any());
		assertThat(view.isFowward(), CoreMatchers.is(true));
		
	}

}
