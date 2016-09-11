package jspboard.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.Controller;
import framework.ModelAndView;
import jspboard.BoardVO;
import jspboard.service.BoardService;
import jspboard.service.BoardServiceImpl;
import util.WebUtil;

public class UpdateController implements Controller
{
	private BoardService service;
	public UpdateController()
	{
		this.service = new BoardServiceImpl();
	}
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException
	{
		// ���� �����
		//request.setCharacterEncoding("utf-8");
		
//		BoardVO board = new BoardVO();
//		board.setPostNo(Integer.parseInt(request.getParameter("postNo")));
//		board.setTitle(request.getParameter("title"));
//		board.setWriter(request.getParameter("writer"));
//		board.setContent(request.getParameter("content"));
		
		BoardVO board = (BoardVO)WebUtil.getParamToVO(BoardVO.class, request);

		service.updateBoard(board);
		
//		response.sendRedirect("list.do");		
		return new ModelAndView("redirect:list.do");
	}	
}