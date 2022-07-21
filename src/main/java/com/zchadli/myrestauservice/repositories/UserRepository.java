package com.zchadli.myrestauservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zchadli.myrestauservice.entities.RestauUser;
import com.zchadli.myrestauservice.entities.Role;

public interface UserRepository extends JpaRepository<RestauUser, Long> {
    public RestauUser findByUsername(String username);

    public Page<RestauUser> findByUsernameNotAndUsernameContaining(String username, String keyword, Pageable pageable);
    
    public Page<RestauUser> findByUsernameNotAndUsernameContainingAndRoles(String username, String keyword, Role role, Pageable pageable);
}
