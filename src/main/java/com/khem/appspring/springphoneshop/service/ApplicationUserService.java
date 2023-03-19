package com.khem.appspring.springphoneshop.service;

import java.util.Optional;

import com.khem.appspring.springphoneshop.config.security.AuthUser;

public interface ApplicationUserService {
   Optional<AuthUser> loadUserByUserName(String username);
}
