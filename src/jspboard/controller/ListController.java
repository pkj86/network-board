package jspboard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.Controller;
import framework.ModelAndView;
import jspboard.BoardVO;
import jspboard.service.BoardService;
import jspboard.service.BoardServiceImpl;

public class ListController implements Controller
{
	private BoardService service;
	
	public ListController()
	{
		service = new BoardServiceImpl();
	}
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException
	{
		List<BoardVO> list = service.list();
		
//		String pattern = "yyyy-MM-dd";
//		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		
		//String listCount = ""+list.size();			
//		request.setAttribute("list", list);
//		RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
//		rd.forward(request, response);
		ModelAndView mav = new ModelAndView("list.jsp");
		mav.addAttribute("list", list);
		return mav;
	}	
}
