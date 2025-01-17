package com.bong.board.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bong.board.domain.dto.BoardDto;

@Service
public class FileService {

	private String uploadPath = "C:\\bongfile\\";;
    
    public void init() {
        try {
            Files.createDirectories(Paths.get(uploadPath));
        } catch (IOException e) {
            throw new RuntimeException("폴더 생성 실패!");
        }
    }

    public void store(MultipartFile file, BoardDto boardDto) {
    	
        try {
            if (file.isEmpty()) {
                throw new Exception("파일이 존재하지 않습니다.");
            }
            Path root = Paths.get(uploadPath);
            if (!Files.exists(root)) {
                init();
            }

            try (InputStream inputStream = file.getInputStream()) {
            	
            	String originalFileName = file.getOriginalFilename(); //실제 파일명 
        		String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); //확장자
        		String saveFileName = UUID.randomUUID()+extension; //저정할 파일명 --> UUID+확장자
        		
                Files.copy(inputStream, root.resolve(saveFileName),
                    StandardCopyOption.REPLACE_EXISTING);
              
        		
        		boardDto.setFileName(originalFileName);
        		boardDto.setFilePath("/bongfile/" + saveFileName);
                
            }
        } catch (Exception e) {
            throw new RuntimeException("저장 실패: " + e.getMessage());
        }
    }

    
    
    
}
