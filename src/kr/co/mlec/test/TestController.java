package kr.co.mlec.test;

import framework.Controller;
import framework.ModelAndView;
import framework.RequestMapping;

@Controller
public class TestController
{
	@RequestMapping("/test/list.do")
	public ModelAndView gogogo(String msg)
	{
		ModelAndView mav = new ModelAndView("/test/list.jsp");
		mav.addAttribute("msg", msg);
		return mav;
	}
}
