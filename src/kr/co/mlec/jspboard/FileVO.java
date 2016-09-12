package kr.co.mlec.jspboard;

public class FileVO
{
	private int fileNo;
	private int postNo;
	private String path;
	private String oriName;
	private String realName;
	private long fileSize;
	
	public int getFileNo()
	{
		return fileNo;
	}
	public void setFileNo(int fileNo)
	{
		this.fileNo = fileNo;
	}
	public int getPostNo()
	{
		return postNo;
	}
	public void setPostNo(int postNo)
	{
		this.postNo = postNo;
	}
	public String getPath()
	{
		return path;
	}
	public void setPath(String realPath)
	{
		this.path = realPath;
	}
	public String getOriName()
	{
		return oriName;
	}
	public void setOriName(String oriName)
	{
		this.oriName = oriName;
	}
	public String getRealName()
	{
		return realName;
	}
	public void setRealName(String realName)
	{
		this.realName = realName;
	}
	public long getFileSize()
	{
		return fileSize;
	}
	public void setFileSize(long fileSize)
	{
		this.fileSize = fileSize;
	}
}
