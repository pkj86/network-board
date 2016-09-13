package kr.co.mlec.jspboard;

import java.util.Date;

public class BoardVO
{
	private int postNo;
	private int viewCnt;
	private String writer;
	private String title;
	private String content;
	private String writerId;
	private Date regDate;
	
	public int getViewCnt()
	{
		return viewCnt;
	}
	public void setViewCnt(int viewCnt)
	{
		this.viewCnt = viewCnt;
	}
	public String getWriterId()
	{
		return writerId;
	}
	public void setWriterId(String writerId)
	{
		this.writerId = writerId;
	}	
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
