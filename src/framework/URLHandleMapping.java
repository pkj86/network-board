package framework;

import java.util.HashMap;
import java.util.Map;

import jspboard.controller.DeleteController;
import jspboard.controller.DetailContoller;
import jspboard.controller.ListController;
import jspboard.controller.ReplyController;
import jspboard.controller.ReplyDeleteController;
import jspboard.controller.ReplyListController;
import jspboard.controller.ReplyUpdateController;
import jspboard.controller.UpdateController;
import jspboard.controller.UpdateFormContoller;
import jspboard.controller.WriteController;
import jspboard.controller.WriteFormController;
import login.LoginController;
import login.LoginFormController;
import login.LogoutController;

public class URLHandleMapping
{
	private Map<String, Controller> mappings;
	public URLHandleMapping(){}
	public URLHandleMapping(String ctrlNames)
	{
		mappings = new HashMap<>();
		String[] Names = ctrlNames.split(";");
		for(int i = 0; i<Names.length; i++)
		{
			Names[i] = Names[i].trim();
			String[] maps = Names[i].split("=");
			Class<?> clz;
			try {
				clz = Class.forName(maps[1]);
				Object controller = clz.newInstance();
				mappings.put(maps[0],(Controller)controller);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		mappings.put("/jspboard/list.do", new ListController());
//		mappings.put("/jspboard/detail.do", new DetailContoller());
//		mappings.put("/jspboard/delete.do", new DeleteController());
//		mappings.put("/jspboard/updateForm.do", new UpdateFormContoller());
//		mappings.put("/jspboard/update.do", new UpdateController());
//		mappings.put("/jspboard/write.do", new WriteController());
//		mappings.put("/jspboard/writeForm.do", new WriteFormController());
//		mappings.put("/jspboard/writeReply.do", new ReplyController());
//		mappings.put("/jspboard/deleteReply.do", new ReplyDeleteController());
//		mappings.put("/jspboard/updateReply.do", new ReplyUpdateController());
//		mappings.put("/jspboard/reply.do", new ReplyListController());
//		mappings.put("/login/loginForm.do", new LoginFormController());
//		mappings.put("/login/login.do", new LoginController());
//		mappings.put("/login/logout.do", new LogoutController());
	}

	public Controller getController(String requestUri)
	{		
		return mappings.get(requestUri);
	}
}
