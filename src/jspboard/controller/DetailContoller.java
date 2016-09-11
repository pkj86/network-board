package jspboard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.Controller;
import framework.ModelAndView;
import jspboard.BoardVO;
import jspboard.FileVO;
import jspboard.ReplyVO;
import jspboard.service.BoardService;
import jspboard.service.BoardServiceImpl;

public class DetailContoller implements Controller
{
	int postNo = -1;
	private BoardService service;
	public DetailContoller()
	{
		this.service = new BoardServiceImpl();
	}
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException
	{
//		HttpSession session = request.getSession();
//		postNo = (int)session.getAttribute("postNo");
		postNo = Integer.parseInt(request.getParameter("postNo"));
		BoardVO vo = service.selectOne(postNo);
		FileVO fVo = service.selectFileByNo(postNo);
//		List<ReplyVO> rList = service.selectAllReply(postNo);
		
		//String listCount = ""+list.size();			
//		request.setAttribute("vo", vo);
//		request.setAttribute("fVo", fVo);
//		request.setAttribute("rList", rList);
//		session.invalidate();
		ModelAndView mav = new ModelAndView("detail.jsp");
		mav.addAttribute("vo", vo);
		mav.addAttribute("fVo", fVo);
//		mav.addAttribute("rList", rList);
//		RequestDispatcher rd = request.getRequestDispatcher("detail.jsp");
//		rd.forward(request, response);
		return mav;
	}
}
