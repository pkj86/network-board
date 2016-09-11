package util;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class WebUtil
{
	public static Object getParamToVO(Class<?> clz, HttpServletRequest request) throws Exception
	{
		Enumeration<String> params = request.getParameterNames();
		List<String> paramNameList = new ArrayList<>();
		List<String> methodNameList = new ArrayList<>();
		Object obj = clz.newInstance();
		Object tVO = null;
		// 파라메터 이름들 죄다 추출
//		while(params.hasMoreElements())
//		{
//			char initial = params.nextElement().charAt(0);
//			paramNameList.add(params.nextElement());
//		}
		// 건너온 클래스의 메소드 모두 추출
		Method[] mArr = clz.getDeclaredMethods();
		/** set....get.....~~~ */
		for(Method method : mArr)
		{
			if(method.getName().startsWith("set"))
			{
				/** set~~~ set~~~~ */
				String cutedName = method.getName().substring("set".length()).toLowerCase();
				methodNameList.add(cutedName);	// set 으로 시작하는 메소드 네임들 set 부분 잘라서 소문자로 만들어 리스트에 삽입
				/** postno, content, title...... */
				// 파라미터 정보 추출
				Parameter[] p = method.getParameters();
				for(int i = 0 ; i < p.length ; i++)
				{
					System.out.println("잘린메소드이름"+methodNameList.get(i));
					Type t = p[i].getParameterizedType();
					if(!t.getTypeName().equalsIgnoreCase("int"))
					{
						Class<?> callClz = Class.forName(t.getTypeName());
						Method m1 = clz.getDeclaredMethod(method.getName(), callClz);
						System.out.println("메소드네임:"+method.getName()+"매개변수형:"+callClz.getClass());
						m1.invoke(obj, request.getParameter(methodNameList.get(i)));						
					}
					else{
						Method m1 = clz.getDeclaredMethod(method.getName(), int.class);
//						System.out.println("메소드네임:"+method.getName()+"매개변수형:"+callClz);
						System.out.println("잘린메소드이름222"+methodNameList.get(i));
						m1.invoke(obj, Integer.parseInt(request.getParameter(methodNameList.get(i))));
					}
				}
			}
//			Type t = p[0].getParameterizedType();
//			System.out.println(t);
		}
		return obj;
	}
}
