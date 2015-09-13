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
import kmj.webboard.util.Utils;

public class PostWriteAction implements IAction {

	@Override
	public View process(ServletContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		
		HttpSession session = request.getSession(false);
		//세션이 있는지 없는지 확인하고 없으면 null 반환
		
		// FIXME 나중에 LoginCheckFilter 에서 처리하도록 합니다.
		if( !Utils.isLogined(session) ){
		   return Views.MOVETOLOGINPAGE(request.getRequestURI());
		    		
	        //simpleboard/login?target= write;
			
			//로긴 안 되어있을 때	
		//return Views.REDIRECT(ctx.getContextPath() + "/login?target=" + "write");
		}  
		
		
		
		
		
		return Views.FORWARD("/WEB-INF/jsp/post-writing-page.jsp");

		}


}
