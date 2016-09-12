package framework;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

public class WebUtil
{
	public static Object getParamToVO(Class<?> clz, HttpServletRequest request) throws Exception
	{
		// 메소드 실행을 위한 target object
		Object obj = clz.newInstance();
		// clz 클래스 정보에 있는 모든 메소드를 가져옴.
		Method[] mArr = clz.getDeclaredMethods();
		for(Method m : mArr)
		{
			String mName = m.getName();
			// 메소드 이름이 set 으로 시작하지 않는 경우는 건너뜀
			if(!mName.startsWith("set")){continue;}
//			System.out.println("메소드 이름 : " + mName);
			mName = mName.substring("set".length());
			mName = Character.toLowerCase(mName.charAt(0)) + mName.substring(1);
			System.out.println("변경된 메소드 이름 : " + mName);
			// set 메소드에 해당하는 파라미터 가져오기.
			String pValue = request.getParameter(mName);
			if(pValue == null){continue;}
			// set 메소드에 설정할 값이 파라미터에 존재함.
			// 메소드를 실행
			// 메소드 객체 = m, 인스턴스 객체 = obj
			// board.setTitle("aaa");
			String pName = m.getParameterTypes()[0].getName();
			System.out.println("파라미터 타입 이름 : " + pName);
			switch(pName)
			{
			case "int":
				m.invoke(obj, Integer.parseInt(pValue));				
				break;
			default:
				m.invoke(obj, pValue);				
				break;
			}
		}
		return obj;
//		Enumeration<String> params = request.getParameterNames();
//		List<String> paramNameList = new ArrayList<>();
//		List<String> methodNameList = new ArrayList<>();
//		Object obj = clz.newInstance();
//		Object tVO = null;
//		// 파라메터 이름들 죄다 추출
////		while(params.hasMoreElements())
////		{
////			char initial = params.nextElement().charAt(0);
////			paramNameList.add(params.nextElement());
////		}
//		// 건너온 클래스의 메소드 모두 추출
//		Method[] mArr = clz.getDeclaredMethods();
//		/** set....get.....~~~ */
//		for(Method method : mArr)
//		{
//			if(method.getName().startsWith("set"))
//			{
//				/** set~~~ set~~~~ */
//				String cutedName = method.getName().substring("set".length()).toLowerCase();
//				methodNameList.add(cutedName);	// set 으로 시작하는 메소드 네임들 set 부분 잘라서 소문자로 만들어 리스트에 삽입
//				/** postno, content, title...... */
//				// 파라미터 정보 추출
//				Parameter[] p = method.getParameters();
//				for(int i = 0 ; i < p.length ; i++)
//				{
//					System.out.println("잘린메소드이름"+methodNameList.get(i));
//					Type t = p[i].getParameterizedType();
//					if(!t.getTypeName().equalsIgnoreCase("int"))
//					{
//						Class<?> callClz = Class.forName(t.getTypeName());
//						Method m1 = clz.getDeclaredMethod(method.getName(), callClz);
//						System.out.println("메소드네임:"+method.getName()+"매개변수형:"+callClz.getClass());
//						m1.invoke(obj, request.getParameter(methodNameList.get(i)));						
//					}
//					else{
//						Method m1 = clz.getDeclaredMethod(method.getName(), int.class);
////						System.out.println("메소드네임:"+method.getName()+"매개변수형:"+callClz);
//						System.out.println("잘린메소드이름222"+methodNameList.get(i));
//						m1.invoke(obj, Integer.parseInt(request.getParameter(methodNameList.get(i))));
//					}
//				}
//			}
////			Type t = p[0].getParameterizedType();
////			System.out.println(t);
//		}
//		return obj;
	}
}
