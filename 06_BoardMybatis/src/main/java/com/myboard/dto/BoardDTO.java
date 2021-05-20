package com.myboard.dto;

import java.util.Date; // sql�� �ִ� �ͺ��� ���� ����

import lombok.Data;

@Data // getter, setter ������ �ʰ�!
public class BoardDTO {
	// ��ȣ, ����, �ۼ���, ����, �ۼ���, ��ȸ��, ��� ����
	private int num;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	private int hitcount;
	private int replyCnt;
}
