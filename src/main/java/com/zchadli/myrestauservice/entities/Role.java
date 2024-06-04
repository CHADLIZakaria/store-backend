package com.zchadli.myrestauservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Override
    public boolean equals(Object object) {
        if(this == object) return  true;
        if(object==null || getClass() != object.getClass()) return false;
        Role role = (Role) object;
        return getId().equals(role.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
