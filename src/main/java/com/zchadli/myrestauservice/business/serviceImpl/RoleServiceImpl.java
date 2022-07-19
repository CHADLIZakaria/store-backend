package com.zchadli.myrestauservice.business.serviceImpl;

import org.springframework.stereotype.Service;

import com.zchadli.myrestauservice.business.service.RoleService;
import com.zchadli.myrestauservice.entities.Role;
import com.zchadli.myrestauservice.exceptions.BusinessException;
import com.zchadli.myrestauservice.exceptions.ErrorsMessage;
import com.zchadli.myrestauservice.repositories.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    
    @Override
    public Role save(Role role) {
        if(! roleRepository.existsByName(role.getName())) {
            return roleRepository.save(role);
        }
        else {
            return findByName(role.getName());
        }
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new BusinessException(ErrorsMessage.USERNAME_NOT_FOUND));
    }

}
