package kmj.webboard.action.page;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kmj.webboard.action.IAction;
import kmj.webboard.action.View;
/**
 * "/join"
 * @author Administrator
 *
 */
public class PageJoinAction implements IAction {

	@Override
	public View process(ServletContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		if ( session != null) {
			String userId = (String)session.getAttribute("userId"); // null
			String userEmail = (String)session.getAttribute("userEmail"); // null
			
			request.setAttribute("formuserId", userId);
			request.setAttribute("formusesrEmail", userEmail);
			request.setAttribute("error", session.getAttribute("error"));
			
			session.invalidate();
		}
	    
//		ctx.getRequestDispatcher("/join.jsp").forward(request, response);
		
		return new View("/join.jsp", true); // 가독성이 안좋음?

	}

}
