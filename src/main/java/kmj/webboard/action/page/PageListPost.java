package kmj.webboard.action.page;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.css.ViewCSS;

import kmj.webboard.action.IAction;
import kmj.webboard.action.View;
import kmj.webboard.action.View.RESPONSE_TYPE;

public class PageListPost implements IAction {

    @Override
    public View process(ServletContext ctx, HttpServletRequest req, HttpServletResponse res) throws IOException {
        
        return new View("/WEB-INF/jsp/list-post.jsp", RESPONSE_TYPE.FORWARD);
    }

}
