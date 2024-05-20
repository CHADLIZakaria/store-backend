package com.zchadli.myrestauservice.business.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.zchadli.myrestauservice.dto.UserDto;
import com.zchadli.myrestauservice.entities.PaginationResponse;

public interface UserService {
    UserDto saveUser(UserDto userDto, MultipartFile file);
    List<UserDto> findAll();
    void deleteById(Long id);
    UserDto findById(Long id);
    UserDto findByUsername(String username);
    Long getNumberUsers();
    PaginationResponse searchUser(int page, int size, String keyword, String username, String roleName);
}
