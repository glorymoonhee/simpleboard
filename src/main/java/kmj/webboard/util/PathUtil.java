package kmj.webboard.util;

import javax.servlet.ServletContext;

public class PathUtil {

    public static String stripURI(ServletContext ctx, String uri) {
        String ctxPath = ctx.getContextPath();
        return uri.substring(ctxPath.length());
    }
}
