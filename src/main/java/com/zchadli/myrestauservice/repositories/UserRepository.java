package com.zchadli.myrestauservice.repositories;

import com.zchadli.myrestauservice.entities.StoreUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zchadli.myrestauservice.entities.Role;

public interface UserRepository extends JpaRepository<StoreUser, Long> {
    public StoreUser findByUsername(String username);

    public Page<StoreUser> findByUsernameNotAndUsernameContaining(String username, String keyword, Pageable pageable);
    
    public Page<StoreUser> findByUsernameNotAndUsernameContainingAndRoles(String username, String keyword, Role role, Pageable pageable);
}
