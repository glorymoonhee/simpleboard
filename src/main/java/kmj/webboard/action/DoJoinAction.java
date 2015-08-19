package kmj.webboard.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kmj.webboard.dao.UserDao;
import kmj.webboard.model.UserVO;

public class DoJoinAction implements IAction {

	@Override
	public View process(ServletContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
	
		
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userid");
		String userEmail = request.getParameter("email");
		String password = request.getParameter("pass");
		
		HttpSession session = request.getSession();
		View view = null;
		try {
			UserDao userDao = (UserDao) ctx.getAttribute("dao.user");
			
			UserVO user = userDao.insertUser(userId, userEmail, password);
			session.setAttribute("user", user);
			
			view = Views.FORWARD( ctx.getContextPath() + "/success");
			// /success
			
		} catch (Exception e) { //예외 발생시
			System.out.println("예외발생");
//			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			session.setAttribute("userEmail", userEmail);
			session.setAttribute("error", "duplicated id or email");
			
			view = Views.REDIRECT( ctx.getContextPath() + "/join" );
		}
		
		return view;
		
	}

}
