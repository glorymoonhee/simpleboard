package kmj.webboard.action.ajax;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kmj.webboard.action.IAction;
import kmj.webboard.action.View;
import kmj.webboard.action.Views;
import kmj.webboard.dao.IPostDao;
/**
 * 엔티티(userVO, postVO)의 생명주기
 * persistent data storage
 * 
 * CRUD 생명주기       SQL(DB)
 * C - create      INSERT ..
 * R - read        SELECT ..
 * U - update      UPDATE ..
 * D - delete      DELETE ..
 * 
 * @author Administrator
 *
 */
public class AjaxPostUpdate implements IAction {

	@Override
	public View process(ServletContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		// TODO 이 글을 쓴 사람과 현재 로그인한 사람이 동일해야함!!!
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String seq = request.getParameter("seq");
		
		IPostDao postdao = (IPostDao)ctx.getAttribute("dao.post");
		postdao.update(seq, title,content);
		
		
		
		JSONObject root = new JSONObject();
		root.put("success",Boolean.TRUE);
		root.put("nextURL", ctx.getContextPath() + "/post/all");
		return Views.JSON(root.toJSONString());
	}

}
