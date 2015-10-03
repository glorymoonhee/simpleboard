package kmj.webboard.action.ajax;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kmj.webboard.action.AppTestBase;
import kmj.webboard.action.View;
import kmj.webboard.action.ajax.AjaxPostWrite;
import kmj.webboard.dao.IPostDao;

public class TestAjaxPostWrite extends AppTestBase {

	@Before
	public void setUp() throws Exception {
		mocker.useSession();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void when_no_login_data_in_session() throws IOException, ServletException, ParseException {
		View view = new AjaxPostWrite().process(boardContext, mocker.httpRequest(), mocker.httpResponse());
		
		JSONObject json = (JSONObject) new JSONParser().parse(view.getJsonData());
		assertThat ( json.get("success"), is(Boolean.FALSE));
	}
	
	@Test
	public void when_valid_login_data_in_session() throws IOException, ServletException, ParseException {
		mocker.sessionAttr("user", anyUser());
		IPostDao postDao = boardContext.getPostDao();
		when ( postDao.insertPost(any(), anyString(), anyString())).thenReturn(anyPost());
		
		View view = new AjaxPostWrite().process(boardContext, mocker.httpRequest(), mocker.httpResponse());
		
		JSONObject json = (JSONObject) new JSONParser().parse(view.getJsonData());
		assertThat ( json.get("success"), is(Boolean.TRUE));
		assertThat(json.get("nextUrl"), notNullValue());
		
	}

}
