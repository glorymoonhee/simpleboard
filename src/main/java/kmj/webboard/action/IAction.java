package kmj.webboard.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAction {

    public View process(ServletContext ctx,
            HttpServletRequest req,
            HttpServletResponse res) throws IOException;
}
