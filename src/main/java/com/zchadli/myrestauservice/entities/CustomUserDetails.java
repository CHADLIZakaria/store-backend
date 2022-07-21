package com.zchadli.myrestauservice.entities;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;
    private RestauUser user;
    Collection<? extends GrantedAuthority> authorities=null;
    private String imagePath;

    public RestauUser getUser() {
        return user;
    }
    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority>  authorities, String imagePath) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.imagePath = imagePath;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities)
    {
        this.authorities=authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
