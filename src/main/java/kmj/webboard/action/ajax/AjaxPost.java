package kmj.webboard.action.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kmj.webboard.action.IAction;
import kmj.webboard.action.View;
import kmj.webboard.action.Views;
import kmj.webboard.dao.PostDao;
import kmj.webboard.model.PostVO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 * ajax - Asynchronous JavaScript and XML 
 * 
 
 [{
	"title" : "글제목이다.",
	"content"  : "asldfkja;sdlkjf;alsdkjf;alsdkjf;alsdkjf;alsdkjf;laskdjfd",
	"writer" : "james"
}]
   
 * @author Administrator
 *
 */
public class AjaxPost implements IAction {

//	List<PostVO> posts = new ArrayList<PostVO>();
	public AjaxPost() {
//		posts.add(new PostVO(10000, "demo", "first content", "2015-08-22 12:11:09", 11, null));
//		posts.add(new PostVO(10233, "second", "second content", "2015-08-22 13:11:09", 123, null));
	}
	@Override
	public View process(ServletContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		/**
		 *  { success : _boolean_,
		 *    posts   : [ {post}, {post} ] 
		 *    
		 *   }
		 *    
		 *    post : {title:??, date:??}
		 */
		JSONObject root = new JSONObject();
		root.put("success", Boolean.TRUE);
		
		
		 PostDao postdao =(PostDao) ctx.getAttribute("dao.post");
		 List<PostVO> posts =  postdao.findAll();
		 
		JSONArray arr = new JSONArray();
		for(int i=0; i<posts.size();i++){
			JSONObject js = new JSONObject();
			js.put("seq", posts.get(i).getSeq());
			js.put("title", posts.get(i).getTitle());
			js.put("writer", posts.get(i).getWriter().getUserId());// 닉넴이었음 
			js.put("date", posts.get(i).getCreationTime());
			js.put("viewcount", posts.get(i).getViewCount());
			js.put("content", posts.get(i).getContent());
			
			arr.add(js);
			
		}
		root.put("posts", arr);
		
		return Views.JSON(root.toJSONString());
	}
	// "success"
//	private String quot(String s) {
//		return "\"" + s + "\"";
//	}
//	
//	
	
	
	/*
	 * JSONObject json = new JSONObject();
		json.put("success", Boolean.TRUE);
		
		JSONArray postData = new JSONArray();
		for(PostVO p : posts) {
			JSONObject post = new JSONObject();
			post.put("seq", p.getSeq());
			post.put("title", p.getTitle());
			post.put("date", p.getCreationTime());
			postData.add(post);
		}
		
		json.put("posts", postData);
		System.out.println("result: " + json.toJSONString());
	 */

}
