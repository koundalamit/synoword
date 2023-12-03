package com.syno.word.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/api/fetch/**").hasRole("READER")
            .antMatchers("/api/**").hasRole("WRITER")
            .antMatchers("/public/**").permitAll()
            .and().exceptionHandling().accessDeniedPage("/access-denied")
            .and().oauth2ResourceServer().jwt()
            .and().userDetailsService(userDetailsServiceBean());
    }
}
