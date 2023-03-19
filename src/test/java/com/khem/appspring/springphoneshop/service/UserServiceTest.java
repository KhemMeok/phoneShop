package com.khem.appspring.springphoneshop.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class UserServiceTest {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String ecoder = passwordEncoder.encode("mypassword2");
        System.out.println(ecoder);
    }
}
