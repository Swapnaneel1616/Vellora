package com.vellora.sb_ecom.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements  FileService{
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //File name of current file
        String originalFileName = file.getOriginalFilename();
        //Generate a unique fileName
        String randomId = UUID.randomUUID().toString();
        // Original filename = mat.jpg , UUID - 1234 . Now---> 1234.jpg
        String fileName = randomId.concat(originalFileName.substring(originalFileName.lastIndexOf('.')));
        String filePath = path + File.pathSeparator +  fileName;
        //Check if path exists
        File folder = new File(path);
        if(!folder.exists()){
            folder.mkdir();
        }

        //Upload to server
        Files.copy(file.getInputStream() , Paths.get(filePath));

        return fileName;
    }
}
