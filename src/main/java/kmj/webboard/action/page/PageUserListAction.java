package kmj.webboard.action.page;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kmj.webboard.action.IAction;
import kmj.webboard.action.View;
import kmj.webboard.action.Views;
import kmj.webboard.dao.IUserDao;
import kmj.webboard.model.UserVO;
import kmj.webboard.util.BoardContext;

public class PageUserListAction implements IAction {

	@Override
	public View process(BoardContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		System.out.println("/users 요청");
		IUserDao userDao =ctx.getUserDao();

		List<UserVO> users = userDao.finaAllUser();
		request.setAttribute("alluser", users);

		// return new View("/WEB-INF/jsp/listAllusers.jsp", true);
		return Views.FORWARD("/WEB-INF/jsp/listAllusers.jsp");

	}

}
