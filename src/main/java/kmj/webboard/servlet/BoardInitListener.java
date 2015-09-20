package kmj.webboard.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import kmj.webboard.action.IAction;
import kmj.webboard.dao.ConnectionFactory;
import kmj.webboard.dao.DBPostDao;
import kmj.webboard.dao.DBUserDao;
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
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("서블릿 컨텍스트를 제거합니다.");

	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("서블릿 컨텍스트가 생성되었습니다.");
		ServletContext ctx = sce.getServletContext();

		// 1. dao 초기화.
		initDao(ctx);
		
		// 2. uri mapping
		initUris(ctx);

		ctx.setAttribute("board-ctx", new BoardContext(ctx));

	}

	private void initUris(ServletContext ctx) {
		
		String uriFiles = "uri.properties";
		ClassLoader loader = BoardInitListener.class.getClassLoader();
		InputStream in = loader.getResourceAsStream(uriFiles);
		Properties prop = new Properties();
		
		Map<String, IAction> actionMap = new HashMap<>();
		try {
			prop.load(in);
			Iterator<Object> itr = prop.keySet().iterator();
			while ( itr.hasNext() ) {
				String path = (String) itr.next();
				String fqClassName = prop.getProperty(path);
				Class<IAction> actionClass = (Class<IAction>) Class.forName(fqClassName);
				
				// reflection
				IAction action = actionClass.newInstance(); // new XXXX();   XXXX.class.newInstance();
				System.out.println(" >>> " + path + ": " + action.getClass().getName());
				actionMap.put(path, action);
				
			}
			ctx.setAttribute("urimapping", actionMap);
			
		} catch (IOException e) {
			throw new RuntimeException("fail to load uri mapping files", e);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		
	}

	private void initDao(ServletContext ctx) {
		ClassLoader loader = BoardInitListener.class.getClassLoader();
		InputStream in = loader.getResourceAsStream("db.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String user = prop.getProperty("product.db.user");
		String pass = prop.getProperty("product.db.pass");
		String url = prop.getProperty("product.db.url");
		System.out.println("user: " + user);
		System.out.println("pass: " + pass);
		System.out.println("url: " + url);
		ConnectionFactory cf = new ConnectionFactory();
		cf.setDburi(url);
		cf.setUser(user);
		cf.setPass(pass);

		ctx.setAttribute("dao.user", new DBUserDao(cf));
		ctx.setAttribute("dao.post", new DBPostDao(cf));
	}

}
