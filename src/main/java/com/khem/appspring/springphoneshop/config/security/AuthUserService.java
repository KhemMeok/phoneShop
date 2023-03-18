package com.khem.appspring.springphoneshop.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.khem.appspring.springphoneshop.service.ApplicationUserService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AuthUserService implements UserDetailsService {

    private final ApplicationUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.loadUserByUserName(username)
        .orElseThrow(()-> new UsernameNotFoundException("User name [%s] is not found".formatted(username)));
    }
    
}
