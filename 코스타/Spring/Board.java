package com.my.dto;

import java.util.Date;

public class Board {
	/*
	 * BOARD_NO                                  NOT NULL NUMBER
 BOARD_PARENT_NO                                    NUMBER
 BOARD_TITLE                                        VARCHAR2(30)
 BOARD_CONTENT                                      VARCHAR2(100)
 BOARD_DT                                           DATE
 BOARD_ID                                           VARCHAR2(5)
 BOARD_VIEWCOUNT                                    NUMBER(4)
	 */
	private int boardNo; //게시글번호
	private int boardParentNo;
	private String boardTitle;
	private String boardContent;
	private Date boardDt;
	private String boardId; //? private Customer boardC;
	private int boardViewcount;
	
	
}
