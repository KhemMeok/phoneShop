package com.khem.appspring.springphoneshop.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import com.khem.appspring.springphoneshop.config.security.RoleEnum;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String fristName;
    private String lastName;
    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;
    // @Enumerated(EnumType.STRING)
    // private RoleEnum roleEnum;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}
