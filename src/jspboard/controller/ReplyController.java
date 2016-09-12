package jspboard.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import framework.Controller;
import framework.ModelAndView;
import framework.WebUtil;
import jspboard.ReplyVO;
import jspboard.service.BoardService;
import jspboard.service.BoardServiceImpl;

public class ReplyController implements Controller
{	
	int postNo = -1;
	private BoardService service;
	public ReplyController()
	{
		this.service = new BoardServiceImpl();
	}
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException
	{
//		postNo = Integer.parseInt(request.getParameter("postNo"));
//		ReplyVO rVo = new ReplyVO();
//		rVo.setPostNo(postNo);
//		rVo.setrWriter(request.getParameter("rWriter"));
//		rVo.setrContent(request.getParameter("rContent"));
		service.insertReply((ReplyVO)WebUtil.getParamToVO(ReplyVO.class, request));
//		HttpSession session = request.getSession();
//		session.setAttribute("postNo", postNo);
//		request.setAttribute("postNo", postNo);
//		response.sendRedirect("detail.do?postNo="+postNo);
//		ModelAndView mav = new ModelAndView("ajax:");
//		mav.addAttribute("postNo", postNo);
		return new ModelAndView("ajax:" + new Gson().toJson(service.replyList(postNo)));		
	}	
}
