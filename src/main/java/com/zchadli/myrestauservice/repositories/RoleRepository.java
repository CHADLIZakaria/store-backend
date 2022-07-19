package com.zchadli.myrestauservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zchadli.myrestauservice.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByName(String name);
    Boolean existsByName(String name);
}
