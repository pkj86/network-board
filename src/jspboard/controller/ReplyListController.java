package jspboard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import framework.Controller;
import framework.ModelAndView;
import jspboard.ReplyVO;
import jspboard.service.BoardService;
import jspboard.service.BoardServiceImpl;

public class ReplyListController implements Controller
{	
	int postNo = -1;
	private BoardService service;
	public ReplyListController()
	{
		this.service = new BoardServiceImpl();
	}
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException
	{
//		ReplyVO rVo = new ReplyVO();
//		rVo.setPostNo(postNo);
//		rVo.setrWriter(request.getParameter("replyWriter"));
//		rVo.setrContent(request.getParameter("replyContent"));
//		service.insertReply(rVo);
//		HttpSession session = request.getSession();
//		session.setAttribute("postNo", postNo);
//		request.setAttribute("postNo", postNo);
//		response.sendRedirect("detail.do?postNo="+postNo);
		return new ModelAndView("ajax:" + new Gson().toJson(service.replyList(Integer.parseInt(request.getParameter("postNo")))));		
		//mav.addAttribute("postNo", postNo);
		//return mav;
	}	
}
