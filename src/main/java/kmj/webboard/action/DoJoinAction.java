package kmj.webboard.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kmj.webboard.dao.IUserDao;
import kmj.webboard.model.UserVO;
import kmj.webboard.util.BoardContext;

public class DoJoinAction implements IAction {

	@Override
	public View process(BoardContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
	
		
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userid");
		String userEmail = request.getParameter("email");
		String password = request.getParameter("pass");
		
		HttpSession session = request.getSession();
		View view = null;
		try {
			IUserDao userDao = ctx.getUserDao();
			
			UserVO user = userDao.insertUser(userId, userEmail, password);
			session.setAttribute("user", user);
			
	        view = Views.REDIRECT( ctx.getContextPath() + "/success" ); // Location: http:/localhost:8080//simppleboard/success
			
		} catch (Exception e) { //예외 발생시(중복되면)
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
