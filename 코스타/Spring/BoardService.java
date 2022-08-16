package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dto.Board;
import com.my.dto.PageBean;
import com.my.exception.FindException;
import com.my.repository.BoardRepository;
@Service
public class BoardService {
	private static final int CNT_PER_PAGE = 3; //페이지별 보여줄 목록수
	@Autowired
	private BoardRepository repository;
	/**
	 * 페이지별 게시글 목록과 페이지그룹정보를 반환한다 
	 * @param currentPage 검색할 페이지
	 * @return 
	 * @throws FindException
	 */
	public PageBean<Board> boardList(int currentPage) throws FindException{
		
		List<Board>list = repository.selectByPage(currentPage, CNT_PER_PAGE);
		int totalCnt = repository.selectCount();//총 행수 12, 13
		
		int cntPerPageGroup = 2;//페이지별 보여줄 페이지수

		PageBean<Board> pb1 = new PageBean<>(list, totalCnt, currentPage, cntPerPageGroup, CNT_PER_PAGE);

		return pb1;
	}
}
