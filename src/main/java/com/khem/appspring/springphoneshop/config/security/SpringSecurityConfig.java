package com.khem.appspring.springphoneshop.config.security;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
    
    private final PasswordEncoder passEcode;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/","index","home").permitAll()
            // .antMatchers("/brands").hasRole("Sale")
            .antMatchers(HttpMethod.POST,"/brands").hasAuthority(PermitionEnum.BRAND_WRITE.getDescription())
            .antMatchers(HttpMethod.GET,"/brands").hasAuthority(PermitionEnum.BRAND_READ.getDescription())
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
         
    }
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        User khim = new User("khim", passEcode.encode("Khim@1234"), Collections.emptyList());
        
        UserDetails tida = User.builder()
            .username("tida")
            .password(passEcode.encode("tida@1234"))
            // .roles("Sale")
            .authorities(RoleEnum.SALE.grantedAuthorities())
            .build();


        UserDetails dara = User.builder()
            .username("dara")
            .password(passEcode.encode("dara@1234"))
            // .roles("Admin")
            .authorities(RoleEnum.ADMIN.grantedAuthorities())
            .build();

        UserDetailsService userDetails = new InMemoryUserDetailsManager(khim,tida,dara);
        return userDetails;
    }
    

    
}
