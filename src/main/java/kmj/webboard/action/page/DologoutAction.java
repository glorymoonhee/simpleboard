package kmj.webboard.action.page;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kmj.webboard.action.IAction;
import kmj.webboard.action.View;
import kmj.webboard.action.Views;

public class DologoutAction implements IAction {

	@Override
	public View process(ServletContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session =request.getSession(false);
		if(session != null){//세션이있다면
			session.invalidate();
			return Views.REDIRECT(ctx.getContextPath());
		}
		
		return Views.REDIRECT(ctx.getContextPath());
	}

}
