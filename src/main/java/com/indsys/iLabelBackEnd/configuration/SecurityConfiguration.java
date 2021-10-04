package com.indsys.iLabelBackEnd.configuration;

import com.indsys.iLabelBackEnd.authentication.service.JwtRequestFilter;
import com.indsys.iLabelBackEnd.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    //allow access for endpoints
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests().antMatchers("/api/register", "/api/auth")
//                .permitAll().anyRequest().authenticated();
//    }

    //exclude certain endpoint from spring security
    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers("/api/register", "/api/auth", "/api/nosecurity", "/api/dateExample", "/api/validity", "/api/product-color/add", "/api/add","/api/trimtype/add","/api/trimtype/list", "/api/label-type/add", "/api/label-type/list");

    }

    //Don't encode passwords
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests().antMatchers("/api/register", "/api/auth")
//        .permitAll().anyRequest().authenticated();
        http.cors().and().addFilterBefore(
                jwtRequestFilter,
                UsernamePasswordAuthenticationFilter.class
        )
        .authorizeRequests().anyRequest().authenticated();

    }
}
