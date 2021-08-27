package com.example.Test.security;

import com.example.Test.service.imp.UserDetailsServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImp userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(authenticationManagerBean());
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/v1/users/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/api/v1/users/login/**").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/v1/users/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/api/v1/users/**").hasAnyAuthority("ADMIN", "TEAMLEADER", "HR");
        http.authorizeRequests().antMatchers("/api/v1/users/{id}/**").hasAnyAuthority("ADMIN", "TEAMLEADER", "HR");
        http.authorizeRequests().antMatchers(POST, "/api/v1/vacancies/**").hasAnyAuthority("ADMIN", "TEAMLEADER");
        http.authorizeRequests().antMatchers(PUT, "/api/v1/vacancies/**").hasAnyAuthority("ADMIN", "HR");
        http.authorizeRequests().antMatchers(GET, "/api/v1/vacancies/**").hasAnyAuthority("ADMIN", "TEAMLEADER", "HR");
        http.authorizeRequests().antMatchers(POST, "/api/v1/{vacancyId}/candidates/**").hasAnyAuthority("ADMIN", "HR");
        http.authorizeRequests().antMatchers(PUT, "/api/v1/{vacancyId}/candidates/**").hasAnyAuthority("ADMIN", "HR");
        http.authorizeRequests().antMatchers(GET, "/api/v1/{vacancyId}/candidates/**").hasAnyAuthority("ADMIN", "TEAMLEADER", "HR");
        http.authorizeRequests().antMatchers(DELETE, "/api/v1/{vacancyId}/candidates/{id}/**").hasAnyAuthority("ADMIN", "HR");
        http.authorizeRequests().antMatchers( "/api/v1/{vacancyId}/candidates/{id}/**").hasAnyAuthority("ADMIN", "TEAMLEADER", "HR");
        http.authorizeRequests().antMatchers(POST, "/api/v1/{candidateId}/sendEmail/**").hasAnyAuthority("ADMIN", "TEAMLEADER", "HR");
        http.authorizeRequests().antMatchers().authenticated();
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(jwtAuthenticationFilter);
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}