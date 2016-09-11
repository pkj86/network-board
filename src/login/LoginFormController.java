package login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.Controller;
import framework.ModelAndView;

public class LoginFormController implements Controller 
{
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException 
	{
//		RequestDispatcher rd = request.getRequestDispatcher("loginForm.jsp");
//		rd.forward(request, response);
		
		return new ModelAndView("loginForm.jsp");
	}
}
















