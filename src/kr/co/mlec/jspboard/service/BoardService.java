package kr.co.mlec.jspboard.service;

import java.util.List;

import kr.co.mlec.jspboard.BoardVO;
import kr.co.mlec.jspboard.FileVO;
import kr.co.mlec.jspboard.ReplyVO;
import kr.co.mlec.member.Member;

public interface BoardService
{
	/** 게시물의 목록정보를 조회하는 기능 */	 
	public List<BoardVO> list() throws Exception;	
	/** 댓글 삭제 처리 */
	public void replyDelete(int delNo) throws Exception;
	/** 댓글 등록 처리 */
	public void insertReply(ReplyVO rVo) throws Exception;
	/** 댓글 수정 처리 */
	public void updateReply(ReplyVO rVo) throws Exception;
	/** 글 삭제 처리 */
	public void deleteBoard(int no) throws Exception;
	/** 글 번호 조회 처리 */
	public BoardVO selectOne(int postNo) throws Exception;
	/** 파일 번호 조회 처리 */
	public FileVO selectFileByNo(int postNo) throws Exception;
	/** 리플 목록조회 처리 */
//	public List<ReplyVO> selectAllReply(int postNo) throws Exception;
	/** 게시물 갱신 처리 */
	public void updateBoard(BoardVO board);
	/** 게시물 등록 처리 */
	public int insertBoard(BoardVO board);
	/** 게시물 등록시 파일 업로드 처리 */
	public void insertFile(FileVO fVo);
	/** 댓글 목록 조회 처리 */
	public List<ReplyVO> replyList(int postNo) throws Exception;
	/** 회원 로그인 처리 */
	public Member loginUser(Member loginUser) throws Exception;
}
