package kmj.webboard.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kmj.webboard.action.DoJoinAction;
import kmj.webboard.action.DoLoginAction;
import kmj.webboard.action.IAction;
import kmj.webboard.action.JoinAction;
import kmj.webboard.action.NotFoundAction;
import kmj.webboard.action.UserListAction;
import kmj.webboard.action.View;
import kmj.webboard.action.ajax.AjaxPost;
import kmj.webboard.action.page.PageAction;
import kmj.webboard.action.page.PageListPost;
import kmj.webboard.dao.UserDao;

import kmj.webboard.util.PathUtil;

/**
 * 
 * "/board/list"          -> list.jsp
 * "/board/view?id=39993" -> view.jsp    "39993 포스트입니다."
 * 
 * 
 */
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext ctx;
	
	private UserDao userDao;
       
	private Map<String, IAction> actionMap = new HashMap<>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        System.out.println("BoardController.init 메소드가 호출되었음");
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	ctx = config.getServletContext();
    	
    	
    	userDao = (UserDao) ctx.getAttribute("dao.user");
    	
    	actionMap.put("get:/board", new PageAction("/WEB-INF/jsp/index.jsp"));
    	actionMap.put("get:/board/login", new PageAction("/WEB-INF/jsp/login.jsp"));
    	actionMap.put("get:/board/join", new JoinAction());
    	actionMap.put("get:/board/users", new UserListAction());
    	actionMap.put("get:/board/post", new PageListPost());
    	actionMap.put("get:/board/post.ajax", new AjaxPost());
    	
    	actionMap.put("post:/board/join", new DoJoinAction());
    	actionMap.put("post:/board/login", new DoLoginAction());
    	
    	actionMap.put("_notfound_", new NotFoundAction());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[doGet]클라이언트로부터 요청이 들어왔음");
		String uri = PathUtil.stripURI(ctx, request.getRequestURI());
		System.out.println("GET uri: " + uri);
		ServletContext ctx =getServletContext();
		
		IAction action = findAction(request.getMethod(), uri);
		
		View view = action.process(ctx, request, response);
		
		if ( view != null) {
		    String resUri = view.getUri();
		    if ( view.isForward() ) {
		        ctx.getRequestDispatcher(resUri).forward(request, response);
		    } else if ( view.isRedirect()) {
		        response.sendRedirect(resUri);
		    } else {
		        // view is json
		        request.setAttribute("json", view.getJSONData());
		        System.out.println("JSON: " + view.getJSONData());
		        ctx.getRequestDispatcher("/WEB-INF/jsp/json/json-writer.jsp").forward(request, response);
		    }
		}
	}

	private IAction findAction(String method, String uri) {
	    IAction action = actionMap.get(method.toLowerCase()+":"+uri);
	    return action != null ? action : actionMap.get("_notfound_");
    }

    /**
	 * RESTful uri로 구성: 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[doPost]클라이언트로부터 요청이 들어왔음");
		String uri = PathUtil.stripURI(ctx, request.getRequestURI());
		System.out.println("POST uri: " + uri);
		ServletContext ctx =getServletContext();

		IAction action = findAction(request.getMethod(), uri);
        
        View view = action.process(ctx, request, response);
        
        if ( view != null) {
            String resUri = view.getUri();
            if ( view.isForward() ) {
                ctx.getRequestDispatcher(resUri).forward(request, response);
            } else if ( view.isRedirect() ) {
                response.sendRedirect(resUri);
            } else {
                // view is json
                request.setAttribute("json", view.getJSONData());
                System.out.println("JSON: " + view.getJSONData());
                ctx.getRequestDispatcher("/WEB-INF/jsp/json/json-writer.jsp").forward(request, response);
            }
        }
	}
	// 회원 가입 정보가 넘어오고 있음.
	/*
	 *    method   uri
	 *    -------  ---------------           
	 *    POST     /board/doJoin 
	 *    -------------------------
	 *    
	 *    request body
	 *    -------------------------
	 *    key            value
	 *    -------------------------
	 *    userid          길이: 6~10
	 *    emial           길이: [6~], '@'가 있어야함.
	 *    pass            길이: [8~12]
	 *    ---------------------------
	 *       
	 */

}
