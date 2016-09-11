package jspboard.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import file.BitFileRenamePolicy;
import framework.Controller;
import framework.ModelAndView;
import jspboard.BoardVO;
import jspboard.FileVO;
import jspboard.service.BoardService;
import jspboard.service.BoardServiceImpl;

public class WriteController implements Controller
{
	private BoardService service;
	public WriteController()
	{
		this.service = new BoardServiceImpl();
	}
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException
	{				
		// ���� ���ε�
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd");
		String path = sdf.format(new Date());
		ServletContext context = request.getServletContext(); 
		String realPath = context.getRealPath("/upload");
		realPath = realPath + path;
		
		File f = new File(realPath);
		if(!f.exists()){f.mkdirs();}				
//		
//		System.out.println("realPath : " + realPath);
		//String realPath = "C:/java86/workspace/ServletJsp/WebContent/upload";
		MultipartRequest mRequest = new MultipartRequest(
				request, 
				realPath,	// ������ ������ �н� 
				1024 * 1024 * 100,	// ������ ������ �ִ� ������ 
				"UTF-8",	// ��û�� ���� ���ڵ� ó�����.
				new BitFileRenamePolicy()	// �����̸��� ���� ����� ��å, �����ϸ� ����Ʈ
				);
		//String msg = mRequest.getParameter("msg");
		//System.out.println("msg : " + msg);
		System.out.println("���� ���� ����");
		
		File file = mRequest.getFile("attachFile");
		// �Խñ� ���ε�
		BoardVO board = new BoardVO();
		board.setTitle(mRequest.getParameter("title"));
		board.setWriter(mRequest.getParameter("writer"));
		board.setContent(mRequest.getParameter("content"));
		// �Խù� ���� ó�� ��Ź..
		int postNo = service.insertBoard(board);
		// ����ڰ� ������ ������ �ִ��� üũ
		if(file != null)
		{
			// ����ڰ� ������ ���ϸ�
			String oriFileName = mRequest.getOriginalFileName("attachFile");
			System.out.println("oriFileName : " + oriFileName);
			// ���� ������ ����� ���ϸ�
			String realFileName = mRequest.getFilesystemName("attachFile");
			System.out.println("realFileName : " + realFileName);
			// ������ ������
			long size = file.length();
			System.out.println("fileSize : " + size);
			FileVO fVo = new FileVO();
			fVo.setPath(path);
			fVo.setPostNo(postNo);
			fVo.setOriName(oriFileName);
			fVo.setRealName(realFileName);
			fVo.setFileSize(size);
			service.insertFile(fVo);			
		}
		// ���� �����
		//request.setCharacterEncoding("utf-8");
//		response.sendRedirect("list.do");
		return new ModelAndView("redirect:list.do");
	}	
}