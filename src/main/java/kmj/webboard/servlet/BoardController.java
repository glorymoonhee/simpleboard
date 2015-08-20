package kmj.webboard.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kmj.webboard.action.DoJoinAction;
import kmj.webboard.action.DoLoginAction;
import kmj.webboard.action.IAction;
import kmj.webboard.action.View;
import kmj.webboard.action.page.NotFoundAction;
import kmj.webboard.action.page.PageInformation;
import kmj.webboard.action.page.PageJoinAction;
import kmj.webboard.action.page.PageJoinSuccessAction;
import kmj.webboard.action.page.PageLoginAction;
import kmj.webboard.action.page.PageUserListAction;
import kmj.webboard.dao.UserDao;
import kmj.webboard.model.UserVO;

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
	
	private Map<String, IAction> actionMap = new HashMap<String, IAction>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        System.out.println("BoardController.init 메소드가 호출되었음");
        
        actionMap.put("/board/join", new PageJoinAction());
        actionMap.put("/board/users", new PageUserListAction());
        actionMap.put("/board/success", new PageJoinSuccessAction());
        actionMap.put("/board/doJoin", new DoJoinAction());
        actionMap.put("/board/login", new PageLoginAction()); 
        actionMap.put("/board/doLogin", new DoLoginAction());
        actionMap.put("_not_found_", new NotFoundAction());
        actionMap.put("/board/myInfo", new PageInformation());
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
		System.out.println("[doGet]클라이언트로부터 요청이 들어왔음");
		ServletContext ctx = getServletContext();

		String uri = stripURI( ctx, request.getRequestURI()); // "/board/join"
		System.out.println("GET uri: " + uri);
		
		/**
		 * command pattern
		 */
		IAction action = findAction ( uri ); // "/board/xxxx"
		
		View view = action.process(ctx, request, response);
		if ( view.isFowward() ) {
			ctx.getRequestDispatcher(view.getUri()).forward(request, response);
		} else {
			response.sendRedirect(view.getUri());
		}
	}


	/**
	 * RESTful uri로 구성: 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[doPost]클라이언트로부터 요청이 들어왔음");
		String uri = stripURI(ctx, request.getRequestURI());
		
		System.out.println("POST uri: " + uri);
		
		IAction action = findAction ( uri ); // "/board/xxxx"
		
		View view = action.process(ctx, request, response);		
		if ( view.isFowward() ) {
			System.out.println("view.isFowward" + view.getUri());// /simpleboard/success
			ctx.getRequestDispatcher(view.getUri()).forward(request, response);;
		
		} else {
			response.sendRedirect(view.getUri());
			
			
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
		String ctxPath = ctx.getContextPath();    // "/simpleboard"
		return requestURI.substring(ctxPath.length()); // "/simpleboard/board/join"
		
	}

}
