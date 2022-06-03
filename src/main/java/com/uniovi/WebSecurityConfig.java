package com.uniovi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

////
@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
//
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
//        http
//                .httpBasic()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/index.html", "/", "/home", "/login","/jornada/**").permitAll()
//                .anyRequest().authenticated().and().csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/**").fullyAuthenticated().and().httpBasic();
//                .antMatchers("/css/**", "/img/**", "/script/**", "/", "/signup", "/login/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .defaultSuccessUrl("/home")
//                .and()
//                .logout()
//                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        // Allow Login API to be accessed without authentication
//        web.ignoring().antMatchers("/login").antMatchers(HttpMethod.OPTIONS, "/**"); // Request type options should be allowed.
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
