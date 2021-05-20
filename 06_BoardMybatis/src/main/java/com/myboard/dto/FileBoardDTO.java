package com.myboard.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileBoardDTO {
	private int num;
	private String title;
	private String writer;
	private String content;
	
	// 이미지 -> resources에 파일 업로드를 위한 것 -> DB Column으로 만들지않음!
	private MultipartFile upload;
	
	// DB에 들어갈 이미지의 이름
	private String fileImage;
}
