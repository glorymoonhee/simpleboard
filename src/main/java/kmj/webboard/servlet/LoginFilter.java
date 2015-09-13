/**
 * 
 */
package kmj.webboard.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kmj.webboard.action.View;
import kmj.webboard.action.Views;
import kmj.webboard.util.Utils;

/**
 * @author Administrator
 *
 */
public class LoginFilter implements Filter {
 private ServletContext ctx;
 private String ctrlPath = "/board";
 private List<String> targetUris = new ArrayList<String>();
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
        ctx = filterConfig.getServletContext(); 
        System.out.println("로로로로로로로로로롤로로로로");
        
        targetUris.add("/post/write");
//        targetUris.add("/post/")
        targetUris.add("/post/write");
        targetUris.add("/post/edit/[0-9]+$");
        targetUris.add("/post/delete/");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
      System.out.println("여기는 로그인 필터입니다");  
      HttpServletRequest req = (HttpServletRequest) request;
      HttpServletResponse res = (HttpServletResponse) response;
      
      String uri = stripURI(ctx, req.getRequestURI()); // "/post/write"
      
      System.out.println("URI FOR LOGIN: " + uri);
      
      if ( checkRequired( uri ) && !checkIfLogined(req, res) ) {
    	  System.out.println("## 로그인이 필요합니다."); 
    	  moveToLoginPage(req, res);
    	  return ;
      }
      
      chain.doFilter(request, response);
	}

	/**
	 * 로그인이 필요한 uri 인지 확인합니다.
	 * @param uri
	 * @return
	 */
	private boolean checkRequired(String uri) {
		
//		return targetUris.contains(uri);
		Iterator<String> itr = targetUris.iterator();
		while ( itr.hasNext()) {
			String pattern = itr.next();
			if ( uri.matches(pattern) ) { //post/read/1000
				return true;
			}
		}
		return false;
	}

	/**
	 * 로그인 정보가 있는지 확인합니다.
	 * @param request
	 * @param response
	 * @return 로그인 되어 있으면 true를 반환합니다.
	 */
	private boolean checkIfLogined(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		return Utils.isLogined(session);
	}
	/**
	 * 
	 * @param request
	 * @param response
	 */
	private void moveToLoginPage( HttpServletRequest request, HttpServletResponse response) {
		String uri = stripURI(ctx, request.getRequestURI());
		System.out.println("로그인 페이지로 리다이렉트함");
		try {
			response.sendRedirect(ctx.getContextPath() + "/login?target="+uri);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {

	}
	
	private String stripURI(ServletContext ctx, String requestURI) {
		String ctxPath = ctx.getContextPath();    // "/simpleboard/board"
		System.out.println("requestURI"+requestURI);
		return requestURI.substring(ctxPath.length()); // "/simpleboard/board/join"
		
	}

}
