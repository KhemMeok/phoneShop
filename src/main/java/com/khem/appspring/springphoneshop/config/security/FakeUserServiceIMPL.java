package com.khem.appspring.springphoneshop.config.security;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.khem.appspring.springphoneshop.service.ApplicationUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FakeUserServiceIMPL implements ApplicationUserService{

    private final PasswordEncoder encoder;

    @Override
    public Optional<AuthUser> loadUserByUserName(String username) {
        
        Optional<AuthUser> findUserByName = getAuthUser().stream().filter(user -> user.getUsername().equals(username)).findFirst();
        
        return findUserByName;
    }
    private List<AuthUser> getAuthUser(){
    AuthUser khem =  new AuthUser(RoleEnum.SALE.grantedAuthorities(), "khem", encoder.encode("123"), true, true, true, true); 
    AuthUser khat =  new AuthUser(RoleEnum.ADMIN.grantedAuthorities(), "khat", encoder.encode("123"), true, true, true, true); 
    return List.of(khem,khat);
    }
}
