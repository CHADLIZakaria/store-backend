package com.zchadli.myrestauservice.business.service;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public void init();
    public String save(MultipartFile file);
    public String update(String oldFileName, MultipartFile file);
    public Resource load(String fileName);
    public boolean deleteFile(String fileName);
}
