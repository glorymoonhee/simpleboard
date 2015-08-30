package kmj.webboard.action;
/**
 * 페이지 이동 정보를 나나태는 클래스입니다.
 * @author Administrator
 *
 */
public class View {

	public enum RESPONSE_TYPE { FORWARD, REDIRECT, JSON };
	
	
	private RESPONSE_TYPE resType ;
	private String uri;
	private String jsonData;
	
	public View ( String uri, RESPONSE_TYPE resType) {
		this.uri = uri;
		this.resType = resType;
	}
	
	public View(String jsonData) {
		this.resType = RESPONSE_TYPE.JSON;
		this.jsonData = jsonData;
	}
	
	public boolean isFowward() {
		return resType == RESPONSE_TYPE.FORWARD;
	}
	
	public boolean isRedirect() {
		return resType == RESPONSE_TYPE.REDIRECT;
	}
	
	public boolean isJson() {
		return resType == RESPONSE_TYPE.JSON;
	}
	
	public String getUri() {
		return uri;
	}
	
	public String getJsonData() {
		return jsonData;
	}
	
}
