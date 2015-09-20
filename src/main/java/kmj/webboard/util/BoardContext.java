package kmj.webboard.util;

import java.util.Map;

import javax.servlet.ServletContext;

import kmj.webboard.action.IAction;
import kmj.webboard.dao.IPostDao;
import kmj.webboard.dao.IUserDao;

public class BoardContext {

	private ServletContext ctx ;
	
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
	/**
	 * uri mapping 정보가 담긴 map 을 반환합니다.
	 * @return
	 */
	public Map<String, IAction> getActionMap() {
		return (Map<String, IAction>) ctx.getAttribute("urimapping");
	}
}
