package kr.co.mlec.test;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

import framework.Controller;
import framework.ModelAndView;
import framework.RequestMapping;
import kr.co.mlec.jspboard.BoardVO;
import kr.co.mlec.jspboard.ReplyVO;
import kr.co.mlec.jspboard.service.BoardService;
import kr.co.mlec.jspboard.service.BoardServiceImpl;
=======
import framework.Controller;
import framework.ModelAndView;
import framework.RequestMapping;
>>>>>>> d60fe643acd7dc7d85c79efd40e69e9d9c8981eb

@Controller
public class TestController
{
<<<<<<< HEAD
	private BoardService service;
	public TestController()
	{
		this.service = new BoardServiceImpl();
	}
	
	@RequestMapping("/test/list.do")
	public ModelAndView gogogo(String msg) throws Exception
	{
		ModelAndView mav = new ModelAndView("/test/list.jsp");
				
=======
	@RequestMapping("/test/list.do")
	public ModelAndView gogogo(String msg)
	{
		ModelAndView mav = new ModelAndView("/test/list.jsp");
>>>>>>> d60fe643acd7dc7d85c79efd40e69e9d9c8981eb
		mav.addAttribute("msg", msg);
		return mav;
	}
}
