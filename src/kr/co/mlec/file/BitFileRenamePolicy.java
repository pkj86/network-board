package kr.co.mlec.file;

import java.io.File;
import java.util.UUID;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class BitFileRenamePolicy implements FileRenamePolicy
{
	@Override
	public File rename(File oriFile)
	{
		String name = oriFile.getName();
		String parent = oriFile.getParent();
		String ext = "";
		int index = name.lastIndexOf(".");
		if(index != -1)
		{
			ext = name.substring(index);
		}
		String uName = UUID.randomUUID().toString();
		return new File(parent, uName + ext);
	}
}
