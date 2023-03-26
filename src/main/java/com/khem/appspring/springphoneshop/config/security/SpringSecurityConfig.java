package com.khem.appspring.springphoneshop.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.khem.appspring.springphoneshop.config.security.jwt.JWTLoginFilter;
import com.khem.appspring.springphoneshop.config.security.jwt.TokenVerifyFiltre;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(
    prePostEnabled = true,
    securedEnabled = true,
    jsr250Enabled = true
)
public class SpringSecurityConfig  {//extends WebSecurityConfigurerAdapter

    private final PasswordEncoder passEcode;

    private final UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

   // @Override
   @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .addFilter(new JWTLoginFilter(authenticationManager(authenticationConfiguration)))
                .addFilterAfter(new TokenVerifyFiltre(), JWTLoginFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/", "index", "home").permitAll()
                // .antMatchers("/models").hasRole("SALE")
                // .antMatchers(HttpMethod.POST, "/brands").hasAuthority(PermitionEnum.BRAND_WRITE.getDescription())
                // .antMatchers(HttpMethod.GET, "/brands").hasAuthority(PermitionEnum.BRAND_READ.getDescription())
                .anyRequest()
                .authenticated();
                // .and()
                // .httpBasic();
        return http.build();
    }
    /*
     * @Bean
     * 
     * @Override
     * protected UserDetailsService userDetailsService() {
     * User khim = new User("khim", passEcode.encode("Khim@1234"),
     * Collections.emptyList());
     * 
     * UserDetails tida = User.builder()
     * .username("tida")
     * .password(passEcode.encode("tida@1234"))
     * // .roles("Sale")
     * .authorities(RoleEnum.SALE.grantedAuthorities())
     * .build();
     * 
     * 
     * UserDetails dara = User.builder()
     * .username("dara")
     * .password(passEcode.encode("dara@1234"))
     * // .roles("Admin")
     * .authorities(RoleEnum.ADMIN.grantedAuthorities())
     * .build();
     * 
     * UserDetailsService userDetails = new
     * InMemoryUserDetailsManager(khim,tida,dara);
     * return userDetails;
     * }
     * 
     */

    //@Override
    // @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(getAuthenticationProvider());
    }

    @Bean
    public AuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passEcode);
        return provider;
    }
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
