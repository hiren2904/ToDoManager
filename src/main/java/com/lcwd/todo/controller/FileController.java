package com.lcwd.todo.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/file")
public class FileController {

	Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@PostMapping("/upload")
	public String uploadSingle(@RequestParam("image") MultipartFile file ) {
		
		logger.info("Name:{}",file.getName());
		logger.info("contenttype : {}",file.getContentType());
		logger.info("OriginalFile name :{}", file.getOriginalFilename());
		logger.info("File size: {}", file.getSize());
		
//		InputStream inputStream = file.getInputStream();
//		FileOutputStream fileoutputstream = new FileOutputStream("data.png");
//		byte data[] = new byte[inputStream.available()];
//		fileoutputstream.write(data);
		
		return "file upload";
	}
	
	@PostMapping("/uploadmultiple")
	public String uploadMultipleFiles(@RequestParam("files")MultipartFile[] files) {
		Arrays.stream(files).forEach(file ->{
			logger.info("File Name :{}"+file.getOriginalFilename());
			logger.info("File Content:{}"+file.getContentType());
			System.out.println("========================================");
			
			//call service to upload files : and pass file object
		});
		return "Handling multiple files";
	}
	
	// serving image file in response
	@GetMapping("/serve-img")
	public void serveImageHandler(HttpServletResponse response) {
		try {
			
			FileInputStream inputstream = new FileInputStream("images/File_test.png");
			response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			StreamUtils.copy(inputstream, response.getOutputStream());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
