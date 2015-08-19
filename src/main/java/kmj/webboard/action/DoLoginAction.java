package kmj.webboard.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kmj.webboard.dao.UserDao;
import kmj.webboard.model.UserVO;

public class DoLoginAction implements IAction {

	@Override
	public View process(ServletContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		String userId = request.getParameter("userid");
		String pass = request.getParameter("pass");
		
		UserDao userdao = (UserDao) ctx.getAttribute("dao.user");
		 UserVO user= userdao.login(userId, pass);
		
		 HttpSession session = request.getSession();
		 if(user!=null){
			
			 session.setAttribute("user", user);
			 return Views.REDIRECT(ctx.getContextPath());
			 
		 } else {
			 session.setAttribute("loginError", Boolean.TRUE);
			 return Views.REDIRECT(ctx.getContextPath() + "/login");
		 }
		  
//		 return null;
	}

}
