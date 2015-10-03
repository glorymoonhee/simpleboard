package kmj.webboard.util;

import javax.servlet.ServletContext;

import kmj.webboard.dao.IPostDao;
import kmj.webboard.dao.IUserDao;

public class BoardContext {

	private ServletContext ctx ;
	final public static String ATTR_POSTDAO = "dao.post";
	final public static String ATTR_USERDAO = "dao.user";
	public static final String ATTR_BOARD_CONTEXT = "board-ctx";
	
	public BoardContext( ServletContext ctx) {
		this.ctx = ctx;
	}
	
	public IUserDao getUserDao() {
		return (IUserDao) ctx.getAttribute("dao.user");
	}
	
	public IPostDao getPostDao() {
		return (IPostDao) ctx.getAttribute("dao.post");
	}

	public String getContextPath() {
		// delegation 을 합니다.
		return ctx.getContextPath();
	}
	/**
	 * 한 페이지에 보여질 게시물의 갯수를 반환합니다.
	 * @return
	 */
	public int getPageSize() {
		return (Integer) ctx.getAttribute("cnt.page") ;
	}
}
