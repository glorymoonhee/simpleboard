package mockservlets;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletMocker {

	private HttpServletRequest req;
	private HttpServletResponse res;
	
	private HttpSession session;
	private ServletContext ctx;
	private ServletConfig config;
	
	public ServletMocker() {
		this("", false);
	}
	
	public ServletMocker(String contextPath) {
		this( contextPath, false);
	}
	
	public ServletMocker(String contextPath, boolean useSession) {
		this.ctx = mock(ServletContext.class);
		this.config = mock(ServletConfig.class);
		this.req = mock(HttpServletRequest.class);
		this.res = mock(HttpServletResponse.class);
		this.session = mock(HttpSession.class);
		
		initMockServletContext(contextPath);
		initMockServletConfig();
		initMockHttpServletRequest();
		initMockHttpSession(useSession);
	}
	
	protected void initMockServletContext( String contextPath ) {
		when ( ctx.getContextPath()).thenReturn(contextPath);
	}
	
	protected void initMockServletConfig () {
		when ( config.getServletContext()).thenReturn(ctx);
	}
	protected void initMockHttpServletRequest() {
		
	}
	
	protected void initMockHttpSession(boolean useSession) {
		when( req.getSession()).thenReturn(session);
		when( req.getSession(true)).thenReturn(session);
		HttpSession mockSession = useSession ? this.session : null;
		when ( req.getSession(false)).thenReturn(mockSession);
	}

	public ServletMocker contextAttr( String key, Object value) {
		when(ctx.getAttribute(key)).thenReturn(value);
		return this;
	}
	
	public ServletMocker configParams(String key, String value) {
		when(config.getInitParameter(key)).thenReturn(value);
		return this;
	}

	public ServletMocker sessionAttr(String key, Object value) {
		when(session.getAttribute(key)).thenReturn(value);
		return this;
	}
	
	public ServletMocker requestAttr ( String key, Object value) {
		when(req.getAttribute(key)).thenReturn(value);
		return this;
	}
	
	public ServletMocker requestParam(String key, String value) {
		when( req.getParameter(key)).thenReturn(value);
		return this;
	}
	

	public HttpServletRequest httpRequest() {
		return req;
	}

	public HttpServletResponse httpResponse() {
		return res;
	}

	public HttpSession httpSession() {
		return session;
	}

	public ServletContext servletContext() {
		return ctx;
	}

	public ServletConfig servletConfig() {
		return config;
	}

	public void setContextPath(String ctxPath) {
		when( ctx.getContextPath()).thenReturn(ctxPath);
	}

	public void useSession() {
		initMockHttpSession(true);
	}
	
	

}
