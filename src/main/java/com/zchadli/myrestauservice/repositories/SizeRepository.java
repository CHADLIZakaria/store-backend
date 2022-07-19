package com.zchadli.myrestauservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zchadli.myrestauservice.entities.Size;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {
    
}
