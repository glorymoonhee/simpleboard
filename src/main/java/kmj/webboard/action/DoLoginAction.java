package kmj.webboard.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kmj.webboard.dao.IUserDao;
import kmj.webboard.model.UserVO;
import kmj.webboard.util.BoardContext;

public class DoLoginAction implements IAction {

	@Override
	public View process(BoardContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		String userId = request.getParameter("userid");
		String pass = request.getParameter("pass");
		String target = request.getParameter("target"); 
		IUserDao userdao = ctx.getUserDao();
		 UserVO user= userdao.login(userId, pass);
		
		 HttpSession session = request.getSession();
		 if(user!=null){
			 
			 session.setAttribute("user", user);
			 
			 if(target!=null){
			 return Views.REDIRECT(ctx.getContextPath()+target);
			 }
			 return Views.REDIRECT(ctx.getContextPath());
			 
		 } else {
			 session.setAttribute("loginError", Boolean.TRUE);
			 
			 return Views.REDIRECT(ctx.getContextPath() + "/login");
		 }

	}

}
