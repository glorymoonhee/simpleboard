package kmj.webboard.util;

import javax.servlet.http.HttpSession;

public class Utils {

	/**
	 * 로그인 정보를 찾아봅니다.
	 * @param session
	 * @return
	 */
	public static boolean isLogined( HttpSession session ) {
		return session != null && session.getAttribute("user") != null  ;
			
	}
}
