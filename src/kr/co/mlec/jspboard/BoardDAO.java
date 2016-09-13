package kr.co.mlec.jspboard;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import common.db.MyAppSqlConfig;
import kr.co.mlec.member.Member;

public class BoardDAO
{
	private static SqlSession sqlMapper;
	
	public BoardDAO()
	{
		try{
			sqlMapper = MyAppSqlConfig.getSqlSessionInstance();			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public int deleteBoard(int delNo)
	{
		int result = sqlMapper.delete("jspboard.BoardDAO.deleteBoard", delNo);
		sqlMapper.commit();
		return result;
	}

	public int insertBoard(BoardVO board)
	{
		sqlMapper.insert("jspboard.BoardDAO.insertBoard", board);
		sqlMapper.commit();
		return board.getPostNo();
	}
	
	public void insertFile(FileVO fVo)
	{
		sqlMapper.insert("jspboard.BoardDAO.insertFile", fVo);
		sqlMapper.commit();
	}

	public List<BoardVO> selectAll()
	{
		List<BoardVO> list = sqlMapper.selectList("jspboard.BoardDAO.getBoardListAll");
		return list;
	}

	public int updateBoard(BoardVO board)
	{
		int result = sqlMapper.update("jspboard.BoardDAO.updateBoard", board); 
		sqlMapper.commit();
		return result;
	}

	public BoardVO selectOne(int findNo)
	{
		BoardVO vo = new BoardVO();
		vo = sqlMapper.selectOne("jspboard.BoardDAO.getBoardListOne", findNo);
		return vo;
	}

	public FileVO selectFileByNo(int findNo)
	{
		FileVO fVo = new FileVO();
		fVo = sqlMapper.selectOne("jspboard.BoardDAO.getFileListOne", findNo);
		return fVo;
	}

	public List<ReplyVO> selectAllReply(int postNo)
	{
		List<ReplyVO> list = sqlMapper.selectList("jspboard.BoardDAO.getReplyListAll", postNo);
		return list;
	}

	public void insertReply(ReplyVO rVo)
	{
		sqlMapper.insert("jspboard.BoardDAO.insertReply", rVo);
		sqlMapper.commit();
	}

	public void deleteReply(int delNo)
	{
		sqlMapper.delete("jspboard.BoardDAO.deleteReply", delNo);
		sqlMapper.commit();		
	}

	public void updateReply(ReplyVO rVo)
	{
		sqlMapper.update("jspboard.BoardDAO.updateReply", rVo); 
		sqlMapper.commit();
	}

	public Member loginUser(Member loginUser)
	{
		return sqlMapper.selectOne("jspboard.BoardDAO.loginUser", loginUser);
	}
	
	public BoardVO temp(int findNo)
	{
		BoardVO vo = new BoardVO();
		vo = sqlMapper.selectOne("jspboard.BoardDAO.getBoardListOne", findNo);
		return vo;
	} 
}