package kmj.webboard.action;

public class View {

    private String uri;
    private View.RESPONSE_TYPE resType;

    public View(String uri, View.RESPONSE_TYPE resType) {
        this.uri = uri;
        this.resType = resType;
    }

    public static enum RESPONSE_TYPE {FORWARD, REDIRECT}

    public String getUri() {
        return uri;
    }

    public boolean isForward() {
        return resType == RESPONSE_TYPE.FORWARD;
    }
    
}