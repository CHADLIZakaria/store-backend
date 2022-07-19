package com.zchadli.myrestauservice.business.service;

import com.zchadli.myrestauservice.entities.Role;

public interface RoleService {
    public Role save(Role role);
    public Role findByName(String name);
}
