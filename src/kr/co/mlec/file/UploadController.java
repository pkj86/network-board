package kr.co.mlec.file;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

@WebServlet("/file/upload")
public class UploadController extends HttpServlet
{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String path = sdf.format(new Date());
		ServletContext context = request.getServletContext(); 
		String realPath = context.getRealPath("/upload");
		realPath = realPath + "/" + path;
		
		File f = new File(realPath);
		f.mkdirs();
		
		
		System.out.println("realPath : " + realPath);
		//String realPath = "C:/java86/workspace/ServletJsp/WebContent/upload";
		MultipartRequest mRequest = new MultipartRequest(
				request, 
				realPath,	// 서버에 저장할 패스 
				1024 * 1024 * 100,	// 저장할 파일의 최대 사이즈 
				"UTF-8",	// 요청에 대한 인코딩 처리방식.
				new BitFileRenamePolicy()	// 파일이름이 같을 경우의 정책, 생략하면 디폴트
				);
		String msg = mRequest.getParameter("msg");
		System.out.println("msg : " + msg);
		System.out.println("파일 저장 성공");
		
		File file = mRequest.getFile("attachFile");
		// 사용자가 선택한 파일이 있는지 체크
		if(file != null)
		{
			// 사용자가 선택한 파일명
			String oriFileName = mRequest.getOriginalFileName("attachFile");
			System.out.println("oriFileName : " + oriFileName);
			// 실제 서버에 저장된 파일명
			String realFileName = mRequest.getFilesystemName("attachFile");
			System.out.println("realFileName : " + realFileName);
			// 파일의 사이즈
			long size = file.length();
			System.out.println("fileSize : " + size);
		}
	}	
}
