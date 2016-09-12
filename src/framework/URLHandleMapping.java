package framework;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class URLHandleMapping
{
	private Map<String, CtrlAndMethod> mappings;
	public URLHandleMapping(){}
	public URLHandleMapping(String ctrlNames)
	{
		mappings = new HashMap<>();
		String[] Names = ctrlNames.split(";");
		for(int i = 0 ; i < Names.length ; i++)
		{
			try
			{
				Class<?> clz = Class.forName(Names[i].trim());
				Object target = clz.newInstance();
				// clz 안의 모든 메소드를 추출
				Method[] mArr = clz.getDeclaredMethods();
				// 반복 진행하며 메소드의 URI 정보를 추출하고 URI에 해당하는 객체와 실행메소드 정보를 맵에 저장
				for(Method m : mArr)
				{
					RequestMapping rm = m.getAnnotation(RequestMapping.class);
					if(rm != null)
					{				
						mappings.put(rm.value(),new CtrlAndMethod(target, m));											
					}
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public CtrlAndMethod getCtrlAndMethod(String requestUri)
	{		
		return mappings.get(requestUri);
	}
}
