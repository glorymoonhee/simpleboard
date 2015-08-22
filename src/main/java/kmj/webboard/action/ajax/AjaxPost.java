package kmj.webboard.action.ajax;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kmj.webboard.action.IAction;
import kmj.webboard.action.View;
import kmj.webboard.action.View.RESPONSE_TYPE;

public class AjaxPost implements IAction {

    @Override
    public View process(ServletContext ctx, HttpServletRequest req, HttpServletResponse res) throws IOException {
        
        String json = String.format("{%s:%s, %s:%s}", 
                quot("success"), "true",
                quot("posts"), quot("OK POST"));
        
        System.out.println(json);
        return new View(json);
    }

    private String quot(String s) {
        return "\"" + s + "\"";
    }

}
