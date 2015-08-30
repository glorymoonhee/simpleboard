package kmj.webboard.servlet;

import java.io.IOException;
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
import kmj.webboard.action.View;
import kmj.webboard.action.ajax.AjaxPost;
import kmj.webboard.action.ajax.AjaxPostWrite;
import kmj.webboard.action.ajax.AjaxUserList;
import kmj.webboard.action.page.NotFoundAction;
import kmj.webboard.action.page.PageInformation;
import kmj.webboard.action.page.PageJoinAction;
import kmj.webboard.action.page.PageJoinSuccessAction;
import kmj.webboard.action.page.PageLoginAction;
import kmj.webboard.action.page.PagePostAll;
import kmj.webboard.action.page.PageUserListAction;
import kmj.webboard.action.page.PostReadPage;
import kmj.webboard.action.page.PostWriteAction;
import kmj.webboard.action.page.TestClick;

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
	
	private String ctrlPath = "/board";
	private Map<String, IAction> actionMap = new HashMap<String, IAction>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        System.out.println("BoardController.init 메소드가 호출되었음");
        
        actionMap.put("/join", new PageJoinAction());
        actionMap.put("/users", new PageUserListAction());
        actionMap.put("/success", new PageJoinSuccessAction());
        actionMap.put("/doJoin", new DoJoinAction());
        actionMap.put("/login", new PageLoginAction()); 
        actionMap.put("/doLogin", new DoLoginAction());
        actionMap.put("_not_found_", new NotFoundAction());
        actionMap.put("/myInfo", new PageInformation());
        actionMap.put("/post/all", new PagePostAll());
        actionMap.put("/post/write", new PostWriteAction());  
        actionMap.put("/post/read", new PostReadPage());  
        // ajax
        actionMap.put("/post.ajax", new AjaxPost());
        actionMap.put("/user.ajax", new AjaxUserList());
        actionMap.put("/post/write.ajax", new AjaxPostWrite());  
        
        
        
        //test
        actionMap.put("/post/test", new TestClick());  

    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	ctx = config.getServletContext();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}


	/**
	 * RESTful uri로 구성: 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getMethod().toUpperCase();
		System.out.println("[" + method + "]클라이언트로부터 요청이 들어왔음");
		String uri = stripURI(ctx, request.getRequestURI());
		
		System.out.println(method + " uri: " + uri);
		
		IAction action = findAction ( uri ); // "/board/xxxx"
		                                         // /board/doJoin
		View view = action.process(ctx, request, response);
		if ( view.isFowward() ) {
			ctx.getRequestDispatcher(view.getUri()).forward(request, response);;
		} else if ( view.isRedirect() ) {
			response.sendRedirect(view.getUri());
		} else {
			request.setAttribute("json", view.getJsonData());
			ctx.getRequestDispatcher("/WEB-INF/jsp/part/json-writer.jsp").forward(request, response);
		}
	}
	
	private IAction findAction(String uri) {
		IAction action = actionMap.get(uri);// /board/join
		if ( action == null) {
			System.out.println("not found: " + uri);
			action = actionMap.get("_not_found_");
		}
		return action;
	}

	private String stripURI(ServletContext ctx, String requestURI) {
		String ctxPath = ctx.getContextPath() + ctrlPath;    // "/simpleboard/board"
		System.out.println("requestURI"+requestURI);
		return requestURI.substring(ctxPath.length()); // "/simpleboard/board/join"
		
	}

}
