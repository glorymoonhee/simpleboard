package kmj.webboard.action.page;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kmj.webboard.action.IAction;
import kmj.webboard.action.View;
import kmj.webboard.action.Views;
import kmj.webboard.dao.PostDao;
import kmj.webboard.model.PostVO;

public class PostReadPage implements IAction {

	@Override
	public View process(ServletContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		PostDao postdao =(PostDao) ctx.getAttribute("dao.post");
		String pid = request.getParameter("pid");
		
		PostVO post = postdao.findbysiq(pid);
		request.setAttribute("post", post);
		
		return Views.FORWARD("/WEB-INF/jsp/postread.jsp");
	}

}
