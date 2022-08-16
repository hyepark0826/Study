package com.my.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
@Component

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
@EqualsAndHashCode(of= {"boardNo"})
@ToString
//@Data
public class Board {	
	private int  level; //글레벨 : 1-원글, 2-답글, 3-답답글, 4-답답답글...
	private int boardNo; //게시글번호
	private int boardParentNo;
	
	private String boardTitle;
	private String boardContent;
	@JsonFormat(pattern = "yy/MM/dd",  timezone = "Asia/Seoul" )
	private Date boardDt;
	
	@NonNull //null로 설정(ex: setBoardId(null) 또는 new Board(~~~, null, ~~)되면 NullPointerException 예외를 일으켜 줍니다.
	private String boardId; //? private Customer boardC;
	private int boardViewcount;
}
