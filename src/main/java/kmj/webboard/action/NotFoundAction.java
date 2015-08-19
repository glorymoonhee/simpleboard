package kmj.webboard.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kmj.webboard.action.View.RESPONSE_TYPE;

public class NotFoundAction implements IAction {

    @Override
    public View process(ServletContext ctx, HttpServletRequest req, HttpServletResponse res) throws IOException {
        
        return new View("/WEB-INF/jsp/not-found-uri.jsp", RESPONSE_TYPE.FORWARD);
    }

}
