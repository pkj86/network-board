package kr.co.mlec.jspboard.service;

import java.util.List;

import kr.co.mlec.jspboard.BoardDAO;
import kr.co.mlec.jspboard.BoardVO;
import kr.co.mlec.jspboard.FileVO;
import kr.co.mlec.jspboard.ReplyVO;
import kr.co.mlec.member.Member;

public class BoardServiceImpl implements BoardService
{
	private BoardDAO dao;
	public BoardServiceImpl()
	{
		dao = new BoardDAO();
	}
	
	@Override
	public List<BoardVO> list() throws Exception
	{		
		return dao.selectAll();	
	}

	@Override
	public void replyDelete(int delNo) throws Exception
	{
		dao.deleteReply(delNo);
	}
	
	@Override
	public void insertReply(ReplyVO rVo) throws Exception
	{
		dao.insertReply(rVo);		
	}

	@Override
	public void deleteBoard(int no) throws Exception
	{
		dao.deleteBoard(no);		
	}

	@Override
	public BoardVO selectOne(int postNo) throws Exception
	{
		return dao.selectOne(postNo);
	}

	@Override
	public FileVO selectFileByNo(int postNo) throws Exception
	{		
		return dao.selectFileByNo(postNo);
	}

//	@Override
//	public List<ReplyVO> selectAllReply(int postNo) throws Exception
//	{
//		return dao.selectAllReply(postNo);
//	}

	@Override
	public void updateBoard(BoardVO board)
	{
		dao.updateBoard(board);		
	}

	@Override
	public int insertBoard(BoardVO board)
	{		
		return dao.insertBoard(board);
	}

	@Override
	public void insertFile(FileVO fVo)
	{
		dao.insertFile(fVo);
	}

	@Override
	public List<ReplyVO> replyList(int postNo) throws Exception
	{
		return dao.selectAllReply(postNo);
	}

	@Override
	public void updateReply(ReplyVO rVo) throws Exception
	{
		dao.updateReply(rVo);
	}

	@Override
	public Member loginUser(Member loginUser) throws Exception
	{		
		return dao.loginUser(loginUser);
	}
}
