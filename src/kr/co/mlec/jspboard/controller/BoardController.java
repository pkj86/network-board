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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;

import framework.Controller;
import framework.ModelAndView;
import framework.RequestMapping;
import framework.RequestParam;
import framework.WebUtil;
import kr.co.mlec.file.BitFileRenamePolicy;
import kr.co.mlec.jspboard.BoardVO;
import kr.co.mlec.jspboard.FileVO;
import kr.co.mlec.jspboard.ReplyVO;
import kr.co.mlec.jspboard.service.BoardService;
import kr.co.mlec.jspboard.service.BoardServiceImpl;
import kr.co.mlec.member.Member;

@Controller
public class BoardController
{
	private BoardService service;
	public BoardController()
	{
		this.service = new BoardServiceImpl();
	}
	
	@RequestMapping("/jspboard/writeReply.do")
	public String writeReplyAjax(int postNo, ReplyVO rVO) throws Exception, IOException
	{
		service.insertReply(rVO);
		return "ajax:" + new Gson().toJson(service.replyList(postNo));		
	}	
	@RequestMapping("/jspboard/deleteReply.do")
	public String deleteReplyAjax(int postNo, int replyNo) throws Exception, IOException 
	{		
		service.replyDelete(replyNo);
		
		return "ajax:" + new Gson().toJson(service.replyList(postNo));
	}
	@RequestMapping("/jspboard/updateReply.do")
	public String updateReplyAjax(@RequestParam("rVO") ReplyVO rVO) throws Exception, IOException 
	{
//		int replyNo = Integer.parseInt(request.getParameter("replyNo"));
//		
//		ReplyVO rVO = new ReplyVO();
//		rVO.setrContent(request.getParameter("replyContent"));
//		rVO.setReplyNo(replyNo);
		service.updateReply(rVO);
		
		return "ajax:{}";
	}
	@RequestMapping("/jspboard/replyList.do")
	public String replyListAjax(int postNo) throws Exception, IOException
	{
		return "ajax:" + new Gson().toJson(service.replyList(postNo));
	}
	@RequestMapping("/jspboard/detail.do")
	public ModelAndView detail(int postNo) throws Exception, IOException
	{
		BoardVO vo = service.selectOne(postNo);
		FileVO fVo = service.selectFileByNo(postNo);
		ModelAndView mav = new ModelAndView("detail.jsp");
		mav.addAttribute("vo", vo);
		mav.addAttribute("fVo", fVo);
		return mav;
	}
	@RequestMapping("/jspboard/delete.do")
	public String delete(int postNo) throws Exception, IOException
	{
		service.deleteBoard(postNo);
		
		return "redirect:list.do";
	}
	@RequestMapping("/jspboard/list.do")
	public ModelAndView list() throws Exception, IOException
	{
		List<BoardVO> list = service.list();
		
		ModelAndView mav = new ModelAndView("list.jsp");
		mav.addAttribute("list", list);
		return mav;
	}
	@RequestMapping("/jspboard/update.do")
	public String update(BoardVO board) throws Exception, IOException
	{		
		//BoardVO board = (BoardVO)WebUtil.getParamToVO(BoardVO.class, request);

		service.updateBoard(board);
		
		return "redirect:list.do";
	}	
	@RequestMapping("/jspboard/updateForm.do")
	public ModelAndView updateForm(int postNo) throws ServletException, IOException
	{
		BoardVO vo = new BoardVO();
		
		ModelAndView mav = new ModelAndView("updateForm.jsp");
		mav.addAttribute("vo", vo);
		return mav;
	}	
	@RequestMapping("/jspboard/write.do")
	public String write(HttpServletRequest request) throws Exception, IOException
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
		HttpSession session = request.getSession();
		Member mem = (Member) session.getAttribute("user");
		board.setWriterId(mem.getId());

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
		return "redirect:list.do";
	}
	@RequestMapping("/jspboard/writeForm.do")
	public void writeForm() throws ServletException, IOException {}
}
