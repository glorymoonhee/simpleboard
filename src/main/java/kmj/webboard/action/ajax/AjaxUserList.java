package kmj.webboard.action.ajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kmj.webboard.action.IAction;
import kmj.webboard.action.View;
import kmj.webboard.action.Views;
import kmj.webboard.dao.IUserDao;
import kmj.webboard.model.UserVO;
import kmj.webboard.util.BoardContext;

public class AjaxUserList implements IAction {
	List<UserVO> users =new ArrayList<UserVO>();


	@Override
	public View process(BoardContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		
		IUserDao userDao = ctx.getUserDao();
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


