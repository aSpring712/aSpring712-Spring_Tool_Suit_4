package com.myboard.dto;

import java.util.Date; // sql에 있는 것보다 넓은 범위

import lombok.Data;

@Data // getter, setter 만들지 않고!
public class BoardDTO {
	// 번호, 제목, 작성자, 내용, 작성일, 조회수, 댓글 개수
	private int num;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	private int hitcount;
	private int replyCnt;
}
