package com.ghev.ToDo.sec;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/TodoList")
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .authorizeRequests()
                .antMatchers("/login","/register","/","/console").permitAll()
                .antMatchers(HttpMethod.POST,"/register").permitAll()
                .anyRequest().authenticated();
    }
}
