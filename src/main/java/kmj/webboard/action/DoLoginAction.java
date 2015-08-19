package kmj.webboard.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kmj.webboard.action.View.RESPONSE_TYPE;
import kmj.webboard.dao.UserDao;
import kmj.webboard.model.UserVO;

public class DoLoginAction implements IAction {

    @Override
    public View process(ServletContext ctx, HttpServletRequest req, HttpServletResponse res) throws IOException {
        UserDao dao = (UserDao) ctx.getAttribute("dao.user");
        
        String userId = req.getParameter("userid");
        String pass   = req.getParameter("password");
        
        UserVO user = dao.login ( userId, pass);
        View view = null;
        if ( user != null ) {
            createSession ( req, user );
            view = new View(ctx.getContextPath() + "/", RESPONSE_TYPE.REDIRECT);
        } else {
            view = new View(ctx.getContextPath() + "/login", RESPONSE_TYPE.REDIRECT);
        }
        return view;
    }

    private void createSession(HttpServletRequest req, UserVO user) {
        HttpSession session = req.getSession(false);
        if ( session != null) {
            session.invalidate();
        }
        session = req.getSession(true);
        session.setAttribute("user", user);
        
    }

}
