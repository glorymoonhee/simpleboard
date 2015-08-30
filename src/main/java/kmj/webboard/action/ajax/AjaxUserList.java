package kmj.webboard.action.ajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kmj.webboard.action.IAction;
import kmj.webboard.action.View;
import kmj.webboard.action.Views;
import kmj.webboard.dao.UserDao;
import kmj.webboard.model.UserVO;

public class AjaxUserList implements IAction {
	List<UserVO> users =new ArrayList<UserVO>();


	@Override
	public View process(ServletContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		
		UserDao userDao = (UserDao) ctx.getAttribute("dao.user");
		 users = userDao.finaAllUser();

		/*{success : boolean
	 * users : {user , user}
	 * }
	 * */
		    
      JSONObject root = new JSONObject();
      
      
      root.put("success", Boolean.TRUE);
      
      
      JSONArray arr = new JSONArray();
		for(int i=0; i<users.size();i++){
			JSONObject js = new JSONObject();
			js.put("userid", users.get(i).getUserId());
			js.put("useremail",users.get(i).getEmail());
	
			arr.add(js);
			
		}
      
		root.put("users", arr);
      
      
		return Views.JSON(root.toJSONString());
	}

}


