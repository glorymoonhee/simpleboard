package kmj.webboard.action.ajax;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import kmj.webboard.action.IAction;
import kmj.webboard.action.View;
import kmj.webboard.action.Views;
import kmj.webboard.dao.PostDao;
import kmj.webboard.model.PostVO;
import kmj.webboard.model.UserVO;

public class AjaxPostWrite implements IAction {

	@Override
	public View process(ServletContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession(false);
		UserVO user = (UserVO)session.getAttribute("user");
		
		JSONObject root = new JSONObject();

		if ( user == null) {
			makeNoLogin(root, session);
		} else {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			PostDao postdao =(PostDao) ctx.getAttribute("dao.post");
			PostVO newPost = postdao.insertPost(user,title,content); 
			/**
			 * { successs : true, nextUrl: '/simpleboard//post/all' }
			 *  
			 *  {success : false, cause : "xxxxx" }
			 */
			
			root.put("success", Boolean.TRUE);
			root.put("nextUrl", ctx.getContextPath() + "/post/all");
			
		}
		
		return Views.JSON(root.toJSONString());
	}

	/**
	 * 로그인 정보가 없을때의 응답.
	 * @param root
	 * @param session
	 */
	private void makeNoLogin(JSONObject root, HttpSession session) {
		
		root.put("success", Boolean.FALSE);
		root.put("cause", "no login info");
	}

}
