package kr.co.mlec.jspboard;

import java.util.Date;

public class BoardVO
{
	private int postNo;
	private String writer;
	private String title;
	private String content;
	private Date regDate;
	
	public int getPostNo()
	{
		return postNo;
	}
	public Date getRegDate()
	{
		return regDate;
	}
	public void setRegDate(Date regDate)
	{
		this.regDate = regDate;
	}
	public void setPostNo(int postNo)
	{
		this.postNo = postNo;
	}
	public String getWriter()
	{
		return writer;
	}
	public void setWriter(String writer)
	{
		this.writer = writer;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	@Override
	public String toString()
	{
		return "BoardVO [postNo=" + postNo + ", writer=" + writer + ", title=" + title + ", content=" + content
				+ ", regDate=" + regDate + "]";
	}
}
