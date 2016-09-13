package kr.co.mlec.jspboard;

import java.util.Date;

public class ReplyVO
{
	private int replyNo;
	private int postNo;
	private String rWriter;
	private String rContent;
	private String rWriterId;
	private Date rRegDate;
	
	public String getrWriterId()
	{
		return rWriterId;
	}
	public void setrWriterId(String rWriterId)
	{
		this.rWriterId = rWriterId;
	}
	public int getReplyNo()
	{
		return replyNo;
	}
	public void setReplyNo(int replyNo)
	{
		this.replyNo = replyNo;
	}
	public int getPostNo()
	{
		return postNo;
	}
	public void setPostNo(int postNo)
	{
		this.postNo = postNo;
	}
	public String getrWriter()
	{
		return rWriter;
	}
	public void setrWriter(String rWriter)
	{
		this.rWriter = rWriter;
	}
	public String getrContent()
	{
		return rContent;
	}
	public void setrContent(String rContent)
	{
		this.rContent = rContent;
	}
	public Date getrRegDate()
	{
		return rRegDate;
	}
	public void setrRegDate(Date rRegDate)
	{
		this.rRegDate = rRegDate;
	}
}
