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

public class PageInformation implements IAction {

	@Override
	public View process(ServletContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
           HttpSession session = request.getSession(false);
               //기존에 세션이 있는지 확인.세션만들지 말고
               //없으면 null, 있으면 session 확인
           if(session!=null){
        	    System.out.println("세션있다");
        	  	return Views.FORWARD("/WEB-INF/jsp/MyInfo.jsp");//바로 url로 myinfo 접근 안되게 막는다.
        	   
           }
           
           System.out.println("세션없다");
          return Views.REDIRECT(ctx.getContextPath()+ "/_not_found_");
	}
       
	
}
