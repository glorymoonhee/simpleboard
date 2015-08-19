package kmj.webboard.action.page;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kmj.webboard.action.IAction;
import kmj.webboard.action.View;
import kmj.webboard.action.Views;
import kmj.webboard.dao.UserDao;
import kmj.webboard.model.UserVO;

public class PageUserListAction implements IAction {

	@Override
	public View process(ServletContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		System.out.println("/users 요청");
		UserDao userDao = (UserDao) ctx.getAttribute("dao.user");

		List<UserVO> users = userDao.finaAllUser();
		request.setAttribute("alluser", users);

		// return new View("/WEB-INF/jsp/listAllusers.jsp", true);
		return Views.FORWARD("/WEB-INF/jsp/listAllusers.jsp");

	}

}
