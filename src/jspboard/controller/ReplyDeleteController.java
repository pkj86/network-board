package jspboard.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import framework.Controller;
import framework.ModelAndView;
import jspboard.service.BoardService;
import jspboard.service.BoardServiceImpl;

//@WebServlet("/board/commentDelete")
public class ReplyDeleteController implements Controller
{
	private BoardService service;
	public ReplyDeleteController()
	{
		this.service = new BoardServiceImpl();
	}
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException 
	{
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		int replyNo = Integer.parseInt(request.getParameter("replyNo"));
		System.out.println(postNo);
		System.out.println(replyNo);
		//BoardDAO dao = new BoardDAO();
		//dao.deleteComment(commentNo);
		service.replyDelete(replyNo);
		
		//response.sendRedirect("detail.do?no=" + postNo);
		return new ModelAndView("ajax:" + new Gson().toJson(service.replyList(postNo)));
	}
}











