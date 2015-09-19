package kmj.webboard.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kmj.webboard.util.BoardContext;

public interface IAction {

	public View process ( BoardContext ctx, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
	
}
