package com.khem.appspring.springphoneshop.config.security;

import static com.khem.appspring.springphoneshop.config.security.PermitionEnum.*;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
@Getter
public enum RoleEnum {

    ADMIN(Set.of(BRAND_READ,BRAND_WRITE)),
    SALE(Set.of(BRAND_READ));

    private Set<PermitionEnum> permitionEnums;

    private RoleEnum(Set<PermitionEnum> permitionEnum){
        this.permitionEnums = permitionEnum;
    }
    public Set<SimpleGrantedAuthority> grantedAuthorities(){
        Set<SimpleGrantedAuthority> permission = this.getPermitionEnums().stream()
            .map(per -> new SimpleGrantedAuthority(per.getDescription()))
            .collect(Collectors.toSet());
            return permission;
    }
}
