package com.khem.appspring.springphoneshop.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    @ManyToMany
    private Set<Permission> permissions;
}
