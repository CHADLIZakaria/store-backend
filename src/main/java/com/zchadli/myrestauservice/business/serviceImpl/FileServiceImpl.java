package com.zchadli.myrestauservice.business.serviceImpl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zchadli.myrestauservice.business.service.FileService;

@Service
public class FileServiceImpl implements FileService {
    private final Path root = Paths.get("upload");

    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        } 
        catch (IOException e) {
           throw new RuntimeException("Could not initialize folder for uploads");
        }
    }

    @Override
    public String save(MultipartFile file) {
       try {
           if(file == null) {
                return "";
            }
            Path path = this.root.resolve(file.getOriginalFilename());
            //Check if exists add 1 before extension fileName1.jpg or fileName2.jpg
            int index = 1;
            while(Files.exists(path)) {
                String[] extensionFile = file.getOriginalFilename().split("\\.");
                path = this.root.resolve(extensionFile[0]+index+"."+extensionFile[1]);
                index++;
            }
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            String url = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/api/files/upload/")
                            .path(path.getFileName().toString())
                            .toUriString();
            return url;
        } 
        catch (IOException e) {
           throw new RuntimeException("Could not store file. error: "+e.getMessage());
        }
    }

    @Override
    public Resource load(String fileName) {
        try {
            Path file = root.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else throw new RuntimeException("Could not read the file");
        }
        catch(MalformedURLException exception) {
            throw new RuntimeException("Error : "+exception.getMessage());
        }
    }

    @Override
    public boolean deleteFile(String fileName) {
        Path file = root.resolve(fileName.substring(fileName.lastIndexOf("/")+1));   
        boolean isDeleted = false;
        try {
            isDeleted =  Files.deleteIfExists(file);
        } 
        catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return isDeleted;
    }

    @Override
    public String update(String oldFileName, MultipartFile file) {
        if(file == null) {
            return oldFileName;
        }
        deleteFile(oldFileName);
        String fileName = save(file);
        return fileName;
    }

  
    
}
