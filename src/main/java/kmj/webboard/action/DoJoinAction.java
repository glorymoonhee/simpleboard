package kmj.webboard.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kmj.webboard.action.View.RESPONSE_TYPE;
import kmj.webboard.dao.UserDao;
import kmj.webboard.model.UserVO;

public class DoJoinAction implements IAction {

    @Override
    public View process(ServletContext ctx, HttpServletRequest req, HttpServletResponse res) throws IOException {
        
        String userId = req.getParameter("userid");
        String userEmail = req.getParameter("email");
        String password = req.getParameter("pass");
        
        System.out.println(String.format("userId:%s, password:%s", userId, password));
        UserDao userDao = (UserDao) ctx.getAttribute("dao.user");
        View view = null;
        HttpSession session = req.getSession();
        try {
            UserVO user = userDao.insertUser(userId, userEmail, password);
            req.setAttribute("user", user);
            view = new View("/WEB-INF/jsp/join-ok.jsp", RESPONSE_TYPE.FORWARD);
            session.invalidate();
        } catch (Exception e) { //예외 발생시
            System.out.println("예외발생");
           
            session.setAttribute("userId", userId);
            session.setAttribute("email", userEmail);
            session.setAttribute("error", "id or email duplication");
            
            view = new View(ctx.getContextPath() + "/join", RESPONSE_TYPE.REDIRECT);
        }
        
        return view;
    }

}
