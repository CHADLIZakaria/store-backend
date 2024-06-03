package com.zchadli.myrestauservice.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestauUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Column(name="first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String password;
    private String imagePath;
    @Column(name="phone_number")
    private String phoneNumber;
    private String sex;
    private String email;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name="users_roles", 
        joinColumns = @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name="users_favorites",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="product_id"))
    private Set<Product> favoriteProducts = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private Set<Review> reviews = new HashSet<>();
    @Override
    public boolean equals(Object object) {
        if(this == object) return  true;
        if(object==null || getClass() != object.getClass()) return false;
        RestauUser user = (RestauUser) object;
        return getId().equals(user.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
