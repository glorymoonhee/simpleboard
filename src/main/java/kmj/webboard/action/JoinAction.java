package kmj.webboard.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class JoinAction implements IAction {

    @Override
    public View process(ServletContext ctx, HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession(false);
        if ( session != null ) {
            String userId = (String) session.getAttribute("userId");
            String email = (String) session.getAttribute("email");
            String error = (String) session.getAttribute("error");
            
            req.setAttribute("formUserId", userId);
            req.setAttribute("formEmail", email);
            req.setAttribute("error", error);
            session.invalidate();
        }
        
//        ctx.getRequestDispatcher("/join.jsp").forward(req, res);
        return new View("/WEB-INF/jsp/join.jsp", View.RESPONSE_TYPE.FORWARD);
    }

}
