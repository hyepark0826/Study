package com.my.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.my.dto.Board;
import com.my.exception.FindException;
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

}
