package com.mycom.myapp06;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.javassist.expr.NewArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.myboard.dto.FileBoardDTO;
import com.myboard.model.BoardService;

@Controller
public class FileController {
	// 로그 사용해서 출력 -> lombok에 있는 로그 사용해도 됨
	private static final Logger log = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private BoardService bservice;
	
	@GetMapping("uploadForm")
	public void uploadForm() {
		
	}
	
	@PostMapping("fileAction")
	public String upload(MultipartFile[] uploads, Model model) { // 파일 여러개를 업로드 할 거라서 배열로 받기, return해서 값 출력을 해주기 위한 Model 객체 생성
		
		String uploadFolder = "C:\\Users\\rnjsq\\Desktop\\06_BoardMybatis\\src\\main\\webapp\\resources";
		ArrayList<String> mfiles = new ArrayList<String>();
		
		for(MultipartFile multipartFile : uploads) {
//			log.info("-------------");
//			log.info("upload File Name : " + multipartFile.getOriginalFilename()); // 파일 이름 가져와서 log로 출력
//			log.info("upload File size : " + multipartFile.getSize()); // 파일 이름 가져와서 log로 출력
//			log.info("-------------");
			
			// 파일 이름의 중복을 피하기 위해 이름 변경
			UUID uuid = UUID.randomUUID(); // 난수 발생
			String uploadFileName = uuid.toString()+"_"+multipartFile.getOriginalFilename(); // 원래 이름 앞에 난수 붙임
			log.info("uploadFileName : " + uploadFileName);
			File saveFile = new File(uploadFolder, uploadFileName);
			
			try {
				multipartFile.transferTo(saveFile);
				mfiles.add(multipartFile.getOriginalFilename());
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			} 
		} // for
		
		model.addAttribute("uploadFileName", mfiles);
		
		return "fileResult";
	}
	
	@GetMapping("fileboard")
	public void fileboard() {
		
	}
	
	@PostMapping("fileInsert")
	public String fileInsert(FileBoardDTO fboard) throws IllegalStateException, IOException { // fboard 안에 파일이 포함되어있음 
		String uploadFolder = "C:\\Users\\rnjsq\\Desktop\\06_BoardMybatis\\src\\main\\webapp\\resources\\img";
		// 파일 업로드 시킨 후
		// fboard에 들어있는 fileImage의 실제 파일명을 넣기
		UUID uuid = UUID.randomUUID(); // 난수 발생
		MultipartFile f = fboard.getUpload(); // 파일 -> Multipart형
		String uploadFileName = ""; // 파일을 선택하지 않으면 공백이 들어감  
		
		if(!f.isEmpty()) { // 파일을 선택한 경우
			uploadFileName = uuid + "_" + f.getOriginalFilename(); // 파일의 원래 이름 앞에 난수를 붙임
			File saveFile = new File(uploadFolder, uploadFileName);			
			f.transferTo(saveFile); // 파일 업로드
//			FileCopyUtils.copy(f.getBytes(), saveFile);
			fboard.setFileImage(uploadFileName); // DB에 파일 이름을 넣어야하니까
		}
		
		bservice.fileInsert(fboard);
		return "redirect:fileList";
	}
	
	@GetMapping("fileList")
	public void fileList(Model model) {
		List<FileBoardDTO> lists = bservice.fileList();
		model.addAttribute("files", lists);
	}
	
}
