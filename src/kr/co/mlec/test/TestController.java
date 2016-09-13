package kr.co.mlec.test;

import java.util.ArrayList;
import java.util.List;

import framework.Controller;
import framework.ModelAndView;
import framework.RequestMapping;
import kr.co.mlec.jspboard.BoardVO;
import kr.co.mlec.jspboard.ReplyVO;
import kr.co.mlec.jspboard.service.BoardService;
import kr.co.mlec.jspboard.service.BoardServiceImpl;

@Controller
public class TestController
{
	private BoardService service;
	public TestController()
	{
		this.service = new BoardServiceImpl();
	}
	
	@RequestMapping("/test/list.do")
	public ModelAndView gogogo(String msg) throws Exception
	{
		ModelAndView mav = new ModelAndView("/test/list.jsp");
		mav.addAttribute("msg", msg);
		return mav;
	}
}
