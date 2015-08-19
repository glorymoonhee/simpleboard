package kmj.webboard.action.page;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kmj.webboard.action.IAction;
import kmj.webboard.action.View;
import kmj.webboard.action.View.RESPONSE_TYPE;

public class PageAction implements IAction {

    private String pageUri;
    
    public PageAction(String uri) {
        pageUri = uri;
    }
    @Override
    public View process(ServletContext ctx, HttpServletRequest req, HttpServletResponse res) throws IOException {
        
        return new View(pageUri, RESPONSE_TYPE.FORWARD);
    }

}
