package com.khem.appspring.springphoneshop.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.khem.appspring.springphoneshop.config.security.AuthUser;
import com.khem.appspring.springphoneshop.exception.ApiException;
import com.khem.appspring.springphoneshop.mapper.UserMapper;
import com.khem.appspring.springphoneshop.model.Role;
import com.khem.appspring.springphoneshop.model.User;
import com.khem.appspring.springphoneshop.repository.UserRepository;

import lombok.RequiredArgsConstructor;
@Primary
@Service
@RequiredArgsConstructor
public class UserSevice implements ApplicationUserService{

    private final UserRepository userRepository;

    @Override
    public Optional<AuthUser> loadUserByUserName(String username) {

        User user = userRepository.findByUsername(username).orElseThrow(()->new ApiException(HttpStatus.BAD_REQUEST,"User (%s) not found".formatted(username,username)));
        AuthUser users = UserMapper.INSTANCE.toUser(user);
        // users.setGrantedAuthorities(user.getRoleEnum().grantedAuthorities());

        Set<SimpleGrantedAuthority> authorities = user.getRoles()
        .stream()
        .flatMap(role -> toStreamOfSimpleGrantedAuthoriry(role)).collect(Collectors.toSet());

        users.setGrantedAuthorities(authorities);

        return Optional.ofNullable(users);
    }

    private Stream<SimpleGrantedAuthority> toStreamOfSimpleGrantedAuthoriry(Role role){

        Set<SimpleGrantedAuthority> permission = role.getPermissions()
        .stream()
        .map(p -> new SimpleGrantedAuthority(p.getName()))
        .collect(Collectors.toSet());

        permission.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));

        return permission.stream();

    }
    
}
