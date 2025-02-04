package com.blog.blogappapis.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.blogappapis.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String Path, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		
		String name = file.getOriginalFilename();
		
		String randomId = UUID.randomUUID().toString();
		String filename1 =randomId.concat(name.substring(name.lastIndexOf(".")));
		
		String filepath = Path +File.separator +filename1;
		
		
		File f = new File(Path);
		if(!f.exists()) {
			f.mkdir();
			}
		
		Files.copy(file.getInputStream(),Paths.get(filepath));
		System.out.print(filename1);
		return filename1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		// TODO Auto-generated method stub
			
		String fullPath = path + File.separator +fileName;
		
		InputStream is = new FileInputStream(fullPath);
		
		return is;
	}

}
