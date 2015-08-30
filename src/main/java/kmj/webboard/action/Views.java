package kmj.webboard.action;


import kmj.webboard.action.View.RESPONSE_TYPE;


/**
 * 도우미 클래스
 * 
 * @author Administrator
 *
 */
public class Views {

	public static View FORWARD( String uri) {
		return new View( uri, RESPONSE_TYPE.FORWARD );
	}
	
	public static View REDIRECT( String uri ) {
		return new View( uri, RESPONSE_TYPE.REDIRECT);
	}

	public static View JSON(String jsonData) {
		return new View ( jsonData );
	}
}
