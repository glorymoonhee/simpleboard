package kmj.webboard.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import kmj.webboard.dao.DBPostDao;
import kmj.webboard.dao.DBUserDao;
import kmj.webboard.dao.MockPostDao;
import kmj.webboard.dao.MockUserDao;
import kmj.webboard.util.BoardContext;

/**
 * 애플리케이션을 초기화하는데 필요한 온갖 설정 정보들을 읽어서 준비하는 단계를 이곳에서 전부 처리를 합니다.
 *
 */
public class BoardInitListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public BoardInitListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("서블릿 컨텍스트를 제거합니다.");
    	
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	new org.mariadb.jdbc.Driver(); // just for registering driver
    	System.out.println("서블릿 컨텍스트가 생성되었습니다.");
    	ServletContext ctx = sce.getServletContext();
    	
//    	ctx.setAttribute("dao.user", new MockUserDao());
//    	ctx.setAttribute("dao.post", new MockPostDao()); 
    	ctx.setAttribute("dao.user", new DBUserDao());
    	ctx.setAttribute("dao.post", new DBPostDao());
    	
//    	ctx.getAttribute("");
    	
    	ctx.setAttribute("board-ctx", new BoardContext(ctx));
    }
	
}
