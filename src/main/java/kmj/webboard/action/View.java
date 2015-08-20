package kmj.webboard.action;
/**
 * 페이지 이동 정보를 나나태는 클래스입니다.
 * @author Administrator
 *
 */
public class View {

	private boolean isFowward;
	private String uri;
	public View ( String uri, boolean isForwarding) {
		this.uri = uri;
		this.isFowward = isForwarding; //true
	}
	public boolean isFowward() {
		return isFowward;
	}
	public String getUri() {
		return uri;
	}
	
}
