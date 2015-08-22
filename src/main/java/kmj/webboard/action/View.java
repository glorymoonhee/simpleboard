package kmj.webboard.action;

public class View {

    private String uri;
    private View.RESPONSE_TYPE resType;

    private String jsonData;
    
    public View(String uri, View.RESPONSE_TYPE resType) {
        this.uri = uri;
        this.resType = resType;
    }
    
    public View(String json) {
        jsonData = json;
        this.resType = RESPONSE_TYPE.JSON;
    }

    public static enum RESPONSE_TYPE {FORWARD, REDIRECT, JSON}

    public String getUri() {
        return uri;
    }
    
    public String getJSONData() {
        return jsonData;
    }

    public boolean isForward() {
        return resType == RESPONSE_TYPE.FORWARD;
    }
    
    public boolean isRedirect() {
        return resType == RESPONSE_TYPE.REDIRECT;
    }
    
    public boolean isJSON() {
        return resType == RESPONSE_TYPE.JSON;
    }
    
}