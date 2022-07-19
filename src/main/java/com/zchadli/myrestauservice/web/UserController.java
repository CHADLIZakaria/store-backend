package com.zchadli.myrestauservice.web;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zchadli.myrestauservice.business.service.UserService;
import com.zchadli.myrestauservice.dto.UserDto;
import com.zchadli.myrestauservice.entities.PaginationResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public UserDto save(UserDto userDto, @RequestParam(name="file", required = false) MultipartFile file) {
        return userService.saveUser(userDto, file);
    }

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public UserDto findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    
    @RequestMapping(value = "/users/{username}/search", method = RequestMethod.GET)
    public PaginationResponse searchAdminUser( 
        @RequestParam(name="page", defaultValue = "0") int page,
        @RequestParam(name="size", defaultValue = "5") int size,
        @RequestParam(name="keyword", defaultValue = "") String keyword,
        @RequestParam(name="role", required = false ) String role,
        @PathVariable String username) {
        return userService.searchUser(page, size, keyword, username,  role);
    }
    
    





}
