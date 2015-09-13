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
import kmj.webboard.dao.IPostDao;
import kmj.webboard.model.PostVO;
import kmj.webboard.util.Utils;

public class PostEditPage implements IAction {

	@Override
	public View process(ServletContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
	    IPostDao postdao = (IPostDao)ctx.getAttribute("dao.post");
		PostVO post = postdao.findbysiq(parsePostSeq(request.getRequestURI()));
		System.out.println("############## " + post);
		request.setAttribute("post", post);
		
		return Views.FORWARD("/WEB-INF/jsp/editpost.jsp");
		
	}

	
	
	private String parsePostSeq(String postReqUri) {
		
		int lastSlashIndex = postReqUri.lastIndexOf("/") + 1 ;
		return postReqUri.substring(lastSlashIndex);
	}
}
