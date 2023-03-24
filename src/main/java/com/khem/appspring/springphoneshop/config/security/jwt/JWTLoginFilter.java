package com.khem.appspring.springphoneshop.config.security.jwt;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter{

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
    
         try {
            LoginRequest LoginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(LoginRequest.getUsername(), LoginRequest.getPassword());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            
            e.printStackTrace();
            throw new RuntimeException(e);
        } 
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
                String key = "Key12$#/2023Key12$#/2023Key12$#/2023Key12$#/2023Key12$#/2023";
            String token = Jwts.builder().setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusMonths(1)))
                .setIssuer("phoneshop.com")
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .compact();
            response.setHeader("Authorization","Bearer "+token);
    }
    
}
