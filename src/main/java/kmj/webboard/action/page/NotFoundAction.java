package kmj.webboard.action.page;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kmj.webboard.action.IAction;
import kmj.webboard.action.View;

public class NotFoundAction implements IAction {

	@Override
	public View process(ServletContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		
		request.setAttribute("invalidURI", request.getRequestURI());
		//ctx.getRequestDispatcher("/WEB-INF/jsp/notfound.jsp").forward(request, response);
		View view = new View("/WEB-INF/jsp/notfound.jsp", true);
		return view;
	}

}
