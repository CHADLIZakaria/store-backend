package com.zchadli.myrestauservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zchadli.myrestauservice.entities.Role;
import com.zchadli.myrestauservice.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);

    public Page<User> findByUsernameNotAndUsernameContaining(String username, String keyword, Pageable pageable);
    
    public Page<User> findByUsernameNotAndUsernameContainingAndRoles(String username, String keyword, Role role, Pageable pageable);
}
