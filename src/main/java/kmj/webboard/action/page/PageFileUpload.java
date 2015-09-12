package kmj.webboard.action.page;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kmj.webboard.action.IAction;
import kmj.webboard.action.View;
import kmj.webboard.action.Views;

public class PageFileUpload implements IAction {

	@Override
	public View process(ServletContext ctx, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		return Views.FORWARD("/WEB-INF/jsp/page-fileupload-demo.jsp");
	}

}
