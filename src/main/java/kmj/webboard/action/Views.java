package kmj.webboard.action;

import java.util.Collections;

/**
 * 도우미 클래스
 * 
 * @author Administrator
 *
 */
public class Views {

	public static View FORWARD( String uri) {
		return new View( uri, true );
	}
	
	public static View REDIRECT( String uri ) {
		return new View( uri, false);
	}
}
