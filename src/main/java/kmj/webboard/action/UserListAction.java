package kmj.webboard.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kmj.webboard.action.View.RESPONSE_TYPE;
import kmj.webboard.dao.UserDao;
import kmj.webboard.model.UserVO;

public class UserListAction implements IAction {

    @Override
    public View process(ServletContext ctx, HttpServletRequest req, HttpServletResponse res) throws IOException {
//        System.out.println("/users 요청");
        
        UserDao userDao = (UserDao) ctx.getAttribute("dao.user");
        List<UserVO> users = userDao.finaAllUser();
        req.setAttribute("alluser", users);
        
//        getServletContext().getRequestDispatcher("/WEB-INF/jsp/listAllusers.jsp").forward(request, response);   
        return new View("/WEB-INF/jsp/listAllusers.jsp", RESPONSE_TYPE.FORWARD);
    }

}
