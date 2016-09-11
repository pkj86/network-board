package jspboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.Controller;
import framework.ModelAndView;
import jspboard.BoardVO;

public class UpdateFormContoller implements Controller
{
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		BoardVO vo = new BoardVO();
		
//		request.setAttribute("vo", vo);
//		RequestDispatcher rd = request.getRequestDispatcher("updateForm.jsp");
//		rd.forward(request, response);
		ModelAndView mav = new ModelAndView("updateForm.jsp");
		mav.addAttribute("vo", vo);
		return mav;
	}	
}
