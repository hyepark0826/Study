package com.my.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.my.dto.Board;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class BoardRepositoryTest {
	@Autowired
	private BoardRepository repository;
	@Test
	public void testSelectByPage() throws FindException {
		int currentPage = 1; //sample 행수 : 7건, 1페이지3건, 2페이지는3건, 3페이지는 1건
		int cntPerPage = 3;
		int expectedSize = 3; 
		int []expectedBoardNoArr = {3, 7, 1};
		List<Board> list = repository.selectByPage(currentPage, cntPerPage);
		assertNotNull(list);
		assertEquals(expectedSize, list.size());
		for(int i=0; i<list.size(); i++) {
			assertEquals(expectedBoardNoArr[i], list.get(i).getBoardNo());
		}
	}
	@Test
	public void testSelectCount() throws FindException {
		int expectedCnt = 7;
		int cnt = repository.selectCount();
		assertEquals(expectedCnt, cnt);
		
		expectedCnt = 3;
		cnt = repository.selectCount("1번");
		assertEquals(expectedCnt, cnt);		
	}
	
	@Test 
	public void testSelectByWord() throws FindException{
		int expectedSize = 1;// 검색어"1번"의 총행수는 3행이다. 1페이지별 2건씩 보면서 2페이지의 행수를 예상
		String word ="1번";
		int currentPage = 2;
		int cntPerPage = 2;
		List<Board> list = repository.selectByWord(word, currentPage, cntPerPage);
		assertEquals(expectedSize, list.size());
	}
	
	@Test 
	public void testSelectByBoardNo() throws FindException{
		String expectedBoardTitle = "3번글";// 검색어"1번"의 총행수는 3행이다. 1페이지별 2건씩 보면서 2페이지의 행수를 예상
		String expectedBoardId = "id1";
		
		int boardNo = 3;
		Board board= repository.selectByBoardNo(boardNo);
		
		assertNotNull(board);
		assertEquals(expectedBoardTitle, board.getBoardTitle());
		assertEquals(expectedBoardId, board.getBoardId());
	}
	
	@Test
	public void testUpdateViewCount() throws ModifyException, FindException{
		//조회수 1증가 테스트
		int boardNo = 1; //1번 글
		
		Board b1 = repository.selectByBoardNo(boardNo);
		assertNotNull(b1);		
		int expectedViewcount = b1.getBoardViewcount()+1; //예상조회수 : 조회수 1증가전의 글 조회수+1
		
		Board b = new Board();
		b.setBoardNo(boardNo);
		b.setBoardViewcount(-1);
		repository.update(b);//조회수 1증가
		
		Board b2 = repository.selectByBoardNo(boardNo); //조회수 1증가후의 글 조회수 
		assertEquals(expectedViewcount, b2.getBoardViewcount());
	}
	
	@Test
	public void testUpdateContent() throws FindException, ModifyException {
		//내용 수정 테스트
		int boardNo = 1;
		Board b1 = repository.selectByBoardNo(boardNo);
		assertNotNull(b1);
		String beforeContent = b1.getBoardContent();
		int beforeViewcount = b1.getBoardViewcount();
		
		String expectedContent = beforeContent+"a";
		Board b = new Board();
		b.setBoardNo(boardNo);
		b.setBoardContent(expectedContent);
		repository.update(b);
		
		Board b2 = repository.selectByBoardNo(boardNo);
		assertNotEquals(beforeContent, b2.getBoardContent());
		assertEquals(expectedContent, b2.getBoardContent());
		assertEquals(beforeViewcount, b2.getBoardViewcount());
	}
	@Test(expected = FindException.class)
	public void testDelete() throws RemoveException, FindException {
		int boardNo = 3;
		repository.delete(boardNo);
		repository.selectByBoardNo(boardNo);
	}
	
	@Test
	public void testInsertBoard() throws AddException, FindException {
		
		//글쓰기용 테스트
		String expectedBoardTitle = "새글";
		String expectedBoardContent = "새글내용";
		String expectedBoardId = "id1";
		Board b = new Board();
		b.setBoardTitle(expectedBoardTitle);
		b.setBoardContent(expectedBoardContent);
		b.setBoardId(expectedBoardId);
		
		repository.insert(b);
		
		int boardNo = b.getBoardNo();
		assertNotEquals(0, boardNo);
		
		Board b1 = repository.selectByBoardNo(boardNo);
		assertNotNull(b1);
		assertEquals(expectedBoardTitle, b1.getBoardTitle());
		assertEquals(expectedBoardContent, b1.getBoardContent());
		assertEquals(expectedBoardId, b1.getBoardId());
		
	}

	@Test
	public void testInsertReply() throws AddException, FindException {
		//답글쓰기용 테스트
		int expectedBoardParentNo = 8;
		String expectedBoardTitle = "새글답";
		String expectedBoardContent = "새글답_내용";
		String expectedBoardId = "id1";
		Board b = new Board();
		b.setBoardParentNo(expectedBoardParentNo);
		b.setBoardTitle(expectedBoardTitle);
		b.setBoardContent(expectedBoardContent);
		b.setBoardId(expectedBoardId);
		
		repository.insert(b);
		
		int boardNo = b.getBoardNo();
		assertNotEquals(0, boardNo);
		
		Board b1 = repository.selectByBoardNo(boardNo);
		assertNotNull(b1);
		assertEquals(expectedBoardTitle, b1.getBoardTitle());
		assertEquals(expectedBoardContent, b1.getBoardContent());
		assertEquals(expectedBoardId, b1.getBoardId());
		
	}
}
