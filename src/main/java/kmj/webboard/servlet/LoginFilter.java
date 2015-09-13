/**
 * 
 */
package kmj.webboard.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 *
 */
public class LoginFilter implements Filter {
 private ServletContext ctx;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
        ctx = filterConfig.getServletContext(); 
        System.out.println("로로로로로로로로로롤로로로로");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
      System.out.println("여기는 로그인 필터입니다");  
      HttpServletRequest req = (HttpServletRequest) request;
      chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
