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
	// �α� ����ؼ� ��� -> lombok�� �ִ� �α� ����ص� ��
	private static final Logger log = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private BoardService bservice;
	
	@GetMapping("uploadForm")
	public void uploadForm() {
		
	}
	
	@PostMapping("fileAction")
	public String upload(MultipartFile[] uploads, Model model) { // ���� �������� ���ε� �� �Ŷ� �迭�� �ޱ�, return�ؼ� �� ����� ���ֱ� ���� Model ��ü ����
		
		String uploadFolder = "C:\\Users\\rnjsq\\Desktop\\06_BoardMybatis\\src\\main\\webapp\\resources";
		ArrayList<String> mfiles = new ArrayList<String>();
		
		for(MultipartFile multipartFile : uploads) {
//			log.info("-------------");
//			log.info("upload File Name : " + multipartFile.getOriginalFilename()); // ���� �̸� �����ͼ� log�� ���
//			log.info("upload File size : " + multipartFile.getSize()); // ���� �̸� �����ͼ� log�� ���
//			log.info("-------------");
			
			// ���� �̸��� �ߺ��� ���ϱ� ���� �̸� ����
			UUID uuid = UUID.randomUUID(); // ���� �߻�
			String uploadFileName = uuid.toString()+"_"+multipartFile.getOriginalFilename(); // ���� �̸� �տ� ���� ����
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
	public String fileInsert(FileBoardDTO fboard) throws IllegalStateException, IOException { // fboard �ȿ� ������ ���ԵǾ����� 
		String uploadFolder = "C:\\Users\\rnjsq\\Desktop\\06_BoardMybatis\\src\\main\\webapp\\resources\\img";
		// ���� ���ε� ��Ų ��
		// fboard�� ����ִ� fileImage�� ���� ���ϸ��� �ֱ�
		UUID uuid = UUID.randomUUID(); // ���� �߻�
		MultipartFile f = fboard.getUpload(); // ���� -> Multipart��
		String uploadFileName = ""; // ������ �������� ������ ������ ��  
		
		if(!f.isEmpty()) { // ������ ������ ���
			uploadFileName = uuid + "_" + f.getOriginalFilename(); // ������ ���� �̸� �տ� ������ ����
			File saveFile = new File(uploadFolder, uploadFileName);			
			f.transferTo(saveFile); // ���� ���ε�
//			FileCopyUtils.copy(f.getBytes(), saveFile);
			fboard.setFileImage(uploadFileName); // DB�� ���� �̸��� �־���ϴϱ�
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
