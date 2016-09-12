package kr.co.mlec.jspboard.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;

import framework.ModelAndView;
import framework.RequestMapping;
import framework.WebUtil;
import kr.co.mlec.file.BitFileRenamePolicy;
import kr.co.mlec.jspboard.BoardVO;
import kr.co.mlec.jspboard.FileVO;
import kr.co.mlec.jspboard.ReplyVO;
import kr.co.mlec.jspboard.service.BoardService;
import kr.co.mlec.jspboard.service.BoardServiceImpl;

public class BoardController
{
	private BoardService service;
	public BoardController()
	{
		this.service = new BoardServiceImpl();
	}
	
	@RequestMapping("/jspboard/writeReply.do")
	public ModelAndView writeReplyAjax(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException
	{
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		service.insertReply((ReplyVO)WebUtil.getParamToVO(ReplyVO.class, request));
		return new ModelAndView("ajax:" + new Gson().toJson(service.replyList(postNo)));		
	}	
	@RequestMapping("/jspboard/deleteReply.do")
	public ModelAndView deleteReplyAjax(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException 
	{
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		int replyNo = Integer.parseInt(request.getParameter("replyNo"));
		
		service.replyDelete(replyNo);
		
		return new ModelAndView("ajax:" + new Gson().toJson(service.replyList(postNo)));
	}
	@RequestMapping("/jspboard/updateReply.do")
	public ModelAndView updateReplyAjax(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException 
	{
		int replyNo = Integer.parseInt(request.getParameter("replyNo"));
		
		ReplyVO rVO = new ReplyVO();
		rVO.setrContent(request.getParameter("replyContent"));
		rVO.setReplyNo(replyNo);
		service.updateReply(rVO);
		
		return new ModelAndView("ajax:{}");
	}
	@RequestMapping("/jspboard/replyList.do")
	public ModelAndView replyListAjax(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException
	{
		return new ModelAndView("ajax:" + new Gson().toJson(service.replyList(Integer.parseInt(request.getParameter("postNo")))));
	}
	@RequestMapping("/jspboard/detail.do")
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException
	{
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		BoardVO vo = service.selectOne(postNo);
		FileVO fVo = service.selectFileByNo(postNo);
		ModelAndView mav = new ModelAndView("detail.jsp");
		mav.addAttribute("vo", vo);
		mav.addAttribute("fVo", fVo);
		return mav;
	}
	@RequestMapping("/jspboard/delete.do")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException
	{
		int no = Integer.parseInt(request.getParameter("postNo"));
		service.deleteBoard(no);
		
		return new ModelAndView("redirect:list.do");
	}
	@RequestMapping("/jspboard/list.do")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException
	{
		List<BoardVO> list = service.list();
		
		ModelAndView mav = new ModelAndView("list.jsp");
		mav.addAttribute("list", list);
		return mav;
	}
	@RequestMapping("/jspboard/update.do")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException
	{		
		BoardVO board = (BoardVO)WebUtil.getParamToVO(BoardVO.class, request);

		service.updateBoard(board);
		
		return new ModelAndView("redirect:list.do");
	}	
	@RequestMapping("/jspboard/updateForm.do")
	public ModelAndView updateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		BoardVO vo = new BoardVO();
		
		ModelAndView mav = new ModelAndView("updateForm.jsp");
		mav.addAttribute("vo", vo);
		return mav;
	}	
	@RequestMapping("/jspboard/write.do")
	public ModelAndView write(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException
	{				
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd");
		String path = sdf.format(new Date());
		ServletContext context = request.getServletContext(); 
		String realPath = context.getRealPath("/upload");
		realPath = realPath + path;
		
		File f = new File(realPath);
		if(!f.exists()){f.mkdirs();}				
		
		MultipartRequest mRequest = new MultipartRequest(
				request, 
				realPath,	
				1024 * 1024 * 100,	
				"UTF-8",	
				new BitFileRenamePolicy()	
				);
				
		File file = mRequest.getFile("attachFile");

		BoardVO board = new BoardVO();
		board.setTitle(mRequest.getParameter("title"));
		board.setWriter(mRequest.getParameter("writer"));
		board.setContent(mRequest.getParameter("content"));

		int postNo = service.insertBoard(board);

		if(file != null)
		{
			String oriFileName = mRequest.getOriginalFileName("attachFile");
			String realFileName = mRequest.getFilesystemName("attachFile");
			long size = file.length();
			FileVO fVo = new FileVO();
			fVo.setPath(path);
			fVo.setPostNo(postNo);
			fVo.setOriName(oriFileName);
			fVo.setRealName(realFileName);
			fVo.setFileSize(size);
			service.insertFile(fVo);			
		}
		return new ModelAndView("redirect:list.do");
	}
	@RequestMapping("/jspboard/writeForm.do")
	public ModelAndView writeForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		return new ModelAndView("writeForm.jsp");
	}
}
