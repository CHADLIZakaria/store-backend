package com.zchadli.myrestauservice.business.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zchadli.myrestauservice.business.service.FileService;
import com.zchadli.myrestauservice.business.service.RoleService;
import com.zchadli.myrestauservice.business.service.UserService;
import com.zchadli.myrestauservice.dto.UserDto;
import com.zchadli.myrestauservice.entities.CustomUserDetails;
import com.zchadli.myrestauservice.entities.PaginationResponse;
import com.zchadli.myrestauservice.entities.RestauUser;
import com.zchadli.myrestauservice.entities.Role;
import com.zchadli.myrestauservice.mapper.RestauMapper;
import com.zchadli.myrestauservice.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private RestauMapper mapper;
    @Autowired
    private FileService fileService;

    @Override
    @Transactional
    public UserDto saveUser(UserDto userDto, MultipartFile file) {
        RestauUser user = mapper.toUser(userDto);
        String pathName = fileService.save(file);
        user.setImagePath(pathName);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(roleService.save(new Role(null, "ROLE_USER")));
        if(userRepository.count() == 0) {
            user.getRoles().add(roleService.save(new Role(null, "ROLE_ADMIN")));
        }
        else {
        }
        return mapper.toUserDto(userRepository.save(user));
    }


    @Override
    public List<UserDto> findAll() {
       return mapper.toUsersDto(userRepository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public UserDto findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RestauUser user = mapper.toUser(findByUsername(username));
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().stream().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new CustomUserDetails(user.getUsername(), user.getPassword(), authorities, user.getImagePath());
    }

    @Override
    public UserDto findByUsername(String username) {
        return mapper.toUserDto(
            userRepository.findByUsername(username));
    }

    @Override
    public Long getNumberUsers() {
        return userRepository.count();
    }

    @Override
    public PaginationResponse searchUser(int page, int size, String keyword, String username, String roleName) {
        Pageable pageable = PageRequest.of(page, size);
        Page<RestauUser> users = null;
        if(roleName == null || roleName == "") {
            users = userRepository.findByUsernameNotAndUsernameContaining(username, keyword, pageable);
        }
        else {
            Role role = roleService.findByName("ROLE_"+roleName);
            users = userRepository.findByUsernameNotAndUsernameContainingAndRoles(username, keyword, role, pageable);
        }
       
        return new PaginationResponse(users.getTotalElements(), size, users.getTotalPages(), page, mapper.toUsersDto(users.getContent()));   
    } 
    
}
