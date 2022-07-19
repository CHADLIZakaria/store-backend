package com.zchadli.myrestauservice.business.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.zchadli.myrestauservice.dto.UserDto;
import com.zchadli.myrestauservice.entities.PaginationResponse;

public interface UserService {
    public UserDto saveUser(UserDto userDto, MultipartFile file);
    public List<UserDto> findAll();
    public void deleteById(Long id);
    public UserDto findById(Long id);
    public UserDto findByUsername(String username);
    public Long getNumberUsers();
    public PaginationResponse searchUser(int page, int size, String keyword, String username, String roleName);
}
