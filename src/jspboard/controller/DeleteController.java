package jspboard.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.Controller;
import framework.ModelAndView;
import jspboard.service.BoardService;
import jspboard.service.BoardServiceImpl;

public class DeleteController implements Controller
{
	private BoardService service;
	public DeleteController()
	{
		this.service = new BoardServiceImpl();
	}
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException
	{
		int no = Integer.parseInt(request.getParameter("postNo"));
		service.deleteBoard(no);
//		response.sendRedirect("list.do");
		
		return new ModelAndView("redirect:list.do");
	}	
}