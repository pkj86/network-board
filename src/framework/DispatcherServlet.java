/**
 * url 패턴 : 
 * /board/list.do -> BoardListController
 * /board/write.do -> BoardWriteController
 * 
 * command 패턴 :
 * /board.do?type=list -> BoardListController
 * /board.do?type=write -> BoardWriteController
 * 
 * 사용자의 요청을 받고
 * 요청에 해당하는 작업 컨트롤러 클래스를 호출하고
 * 작업클래스에서 실행한 결과를 적절한 사용자 화면 페이지를 호출하여 사용할수 있게 한다.
 * 
 * 나중엔...
 * 자동 파라미터 처리
 * 사용자 요청 작업 클래스를 검색하는 부분을 효율적으로 변경
 */
package framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet
{
	private URLHandleMapping mappings;
	
	@Override
	public void init(ServletConfig config) throws ServletException
	{
		String ctrlNames = config.getInitParameter("controllers");
		mappings = new URLHandleMapping(ctrlNames);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		// 현재 프로젝트 경로 찾기
//		ServletContext sc = req.getServletContext();
//		String contextPath = sc.getContextPath();
//		System.out.println("contextPath : " + contextPath);
		// 혹은 이렇게 찾는다
		String contextPath = req.getContextPath();
		System.out.println("contextPath : " + contextPath);
		
		String requestUri = req.getRequestURI();
		System.out.println("requestUri : " + requestUri);
		
		requestUri = requestUri.substring(contextPath.length());
		System.out.println("requestUri : " + requestUri);
		Controller controller = mappings.getController(requestUri);
		
		if(controller == null){ throw new ServletException("요청한 페이지는 존재하지 않습니다"); }
		ModelAndView mav;
		try
		{
			mav = controller.execute(req, res);
			String view = mav.getView();
			if(view.startsWith("redirect:"))
			{
				res.sendRedirect(view.substring("redirect:".length()));
			}
			else if(view.startsWith("ajax:"))
			{
				res.setContentType("text/json; charset=utf-8");
				PrintWriter out = res.getWriter();
				out.println(view.substring("ajax:".length()));
				System.out.println("자르고난뒤:"+view.substring("ajax:".length()));
				out.close();
			}
			else
			{
				// mav 객체의 model 정보를 화면에 사용할수 있게 공유하는 작업 필요
				Map<String, Object> model = mav.getModel();
				Set<String> keys = model.keySet();
				for(String key : keys)
				{
					req.setAttribute(key, model.get(key));				
				}
				RequestDispatcher rd = req.getRequestDispatcher(view);
				rd.forward(req, res);			
			}
		} catch (Exception e)
		{
			throw new ServletException(e);
		}		
	}	
}
