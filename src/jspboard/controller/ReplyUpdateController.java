package jspboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.Controller;
import framework.ModelAndView;
import jspboard.BoardDAO;
import jspboard.ReplyVO;
import jspboard.service.BoardService;
import jspboard.service.BoardServiceImpl;

//@WebServlet("/board/commentUpdate")
public class ReplyUpdateController implements Controller
{	
	private BoardService service;
	public ReplyUpdateController()
	{
		this.service = new BoardServiceImpl();
	}
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException 
	{
		int replyNo = Integer.parseInt(request.getParameter("replyNo"));
		//int no = Integer.parseInt(request.getParameter("no"));
		
		// 게시판과 파일 테이블에 저장할 글번호를 조회
		ReplyVO rVO = new ReplyVO();
		rVO.setrContent(request.getParameter("replyContent"));
		rVO.setReplyNo(replyNo);
		
		// 게시물 저장 처리 부탁..
		//BoardDAO dao = new BoardDAO();
		//dao.updateComment(comment);
		service.updateReply(rVO);
		
		//response.sendRedirect("detail.do?no=" + no);
		return new ModelAndView("ajax:{}");
	}
}











