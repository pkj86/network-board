package kr.co.mlec.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.mlec.member.Member;

public class AuthFilter implements Filter
{
	private List<String> pageList;
	@Override
	public void destroy(){}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		// pageList �� ȣ���� �������� ������ ���
		// ���� �������� �̵�
		// �������� ���� ��� ���ǿ� user ������ ��ϵǾ��ִ��� Ȯ��
		// ������ ���ٸ� loginForm �������� �̵���Ŵ
		// ������ user������ �ִٸ� ���� �������� ȣ��
		HttpServletRequest hRequest = (HttpServletRequest)request;
		String requestUri = hRequest.getRequestURI();
		// /network-board
		String contextPath = hRequest.getContextPath();
		requestUri = requestUri.substring(contextPath.length());
		boolean isRedirect = false;
		int index = pageList.indexOf(requestUri);
		if(index == -1)
		{
			HttpSession session = hRequest.getSession();
			Member user = (Member)session.getAttribute("user");
			if(user == null)
			{
				isRedirect = true;
			}
		}
		if(isRedirect)
		{
			HttpServletResponse hResponse = (HttpServletResponse)response;
			hResponse.sendRedirect(hRequest.getContextPath() + "/login/loginForm.do");
		}else{
			chain.doFilter(request, response);			
		}
	}

	@Override
	public void init(FilterConfig fc) throws ServletException
	{
		String pages = fc.getInitParameter("pages");
//		System.out.println("not login :" + pages);
		String[] pageArr = pages.split(";");
		// �α��� ���� ����Ҽ� �ִ� ������ ������ ����Ʈ�� �߰�
		pageList = new ArrayList<String>();
		for(String p : pageArr)
		{
			pageList.add(p.trim());			
		}
	}
}
