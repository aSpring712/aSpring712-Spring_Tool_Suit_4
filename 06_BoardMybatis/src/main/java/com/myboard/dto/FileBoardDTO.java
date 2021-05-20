package com.myboard.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileBoardDTO {
	private int num;
	private String title;
	private String writer;
	private String content;
	
	// �̹��� -> resources�� ���� ���ε带 ���� �� -> DB Column���� ����������!
	private MultipartFile upload;
	
	// DB�� �� �̹����� �̸�
	private String fileImage;
}
