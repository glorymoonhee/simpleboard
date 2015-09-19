package kmj.webboard.action.ajax;

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
import kmj.webboard.util.BoardContext;
import kmj.webboard.util.Utils;

import org.json.simple.JSONObject;

public class AjaxDeletePage implements IAction {

	@Override
	public View process(BoardContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
	
		IPostDao postdao = ctx.getPostDao();
		String pattern = parsePostSeq(request.getRequestURI());
		 postdao.delete(pattern);	
		
		
		JSONObject json = new JSONObject();
		json.put("success",Boolean.TRUE);
		json.put("nextURL", ctx.getContextPath() + "/post/all");
		
		return Views.JSON(json.toJSONString());
	}
	
private String parsePostSeq(String postReqUri) {
	
		int lastSlashIndex = postReqUri.lastIndexOf("/") + 1 ;
		return postReqUri.substring(lastSlashIndex);
	}
	

}
