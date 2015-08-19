package kmj.webboard.action.page;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kmj.webboard.action.IAction;
import kmj.webboard.action.View;
import kmj.webboard.model.UserVO;

public class PageJoinSuccessAction implements IAction {

	@Override
	public View process(ServletContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		View view = null;
		if ( user == null) {
			// 직접 접근한 듯 ?
			view = new View(ctx.getContextPath() + "/",false);
			
		} else {
			request.setAttribute("user", user);
			session.invalidate();
			view = new View("/WEB-INF/jsp/join-ok.jsp", true);
			//ctx.getRequestDispatcher("/WEB-INF/jsp/join-ok.jsp").forward(request, response);
			
		}
	 return view;
	}
	 

}
