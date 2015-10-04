package kmj.webboard.action.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kmj.webboard.action.IAction;
import kmj.webboard.action.View;
import kmj.webboard.action.Views;
import kmj.webboard.util.BoardContext;

public class PageLoginAction implements IAction {

	@Override
	public View process(BoardContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession(false);
		
		if ( session != null ) {
			
			request.setAttribute("error", session.getAttribute("loginError"));
			session.invalidate();
		}
		
		//세션이없으면
                   
						
                    	 String target = request.getParameter("target");
                    	 if(target!=null){
                    	 request.setAttribute("target", target);
                    	 System.out.println("target은 뭘까요옹"+target);
                    	 }
	
                         	
                    	 return Views.FORWARD("/WEB-INF/jsp/login.jsp");
	}
                    	 
}
