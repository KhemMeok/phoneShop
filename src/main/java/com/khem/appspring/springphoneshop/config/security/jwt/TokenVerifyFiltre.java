package com.khem.appspring.springphoneshop.config.security.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class TokenVerifyFiltre extends OncePerRequestFilter{

//    @Value("${server.key}")
//    static String key;



    @Autowired
    public String getKey;

    String key = getKey;




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                String authorizationHeder = request.getHeader("Authorization");

                if(Objects.isNull(authorizationHeder) || !authorizationHeder.startsWith("Bearer ")){
                    filterChain.doFilter(request, response);
                    return;
                }
                //String key = "Key12$#/2023Key12$#/2023Key12$#/2023Key12$#/2023Key12$#/2023";
                String token = authorizationHeder.replace("Bearer ","");

                try {
                    Jws<Claims> parseClaimsJws = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
                    .parseClaimsJws(token);
                
                Claims body = parseClaimsJws.getBody();
                String username = body.getSubject();
                var authorities = (List<Map<String,String>>) body.get("authorities");
                // Jwts.parserBuilder()
                //     .requireAudience("Key12$#/2023Key12$#/2023Key12$#/2023Key12$#/2023Key12$#/2023")
                //     .build()
                //     .parse(key);    

                Set<SimpleGrantedAuthority> sampleAuthority = authorities.stream()
                            .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                            .collect(Collectors.toSet());
                            


                Authentication authentication = new UsernamePasswordAuthenticationToken(username,null,sampleAuthority);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
                } catch (JwtException e) {
                   log.error(e.getMessage());
                   throw new IllegalStateException("Invalid token - %s".formatted(token));
                }

                
    }
    
}
