package com.example.demo10;

/**
 * Created by TUTEHUB on 2022/1/25 3:23 PM.
 * Copyright © 2022 TUTEHUB. All rights reserved.
 * ------------------------
 * Non-disclosure Terms
 * -------------------------
 * These codes are TUTEHUB's property, as the developer, such as employee, contractor, and intern, working for TUTEHUB cannot disclose, spread, copy, preserve, sell, and do other activities without the consent of TUTEHUB.
 * Developer dph agrees with above terms.
 * Technique Support: jobyme88.com
 * Contact: noreply@fengcaoculture.com
 */


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @description
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/login_error_handler", "/logout_success", "/logout_success_handler").permitAll()
                    .anyRequest().authenticated()


                /**
                 * Login
                 */
                .and()
                    // use the login page
                    .formLogin()
                    // use customized login page
                    // can only use this name
                    .loginPage("/login")
                    // everyone can visit the /login
                    .permitAll()

                    /**
                     * Level 1
                     */
                    // if login successfully
                    // for unsecured endpoint
                    // will be directed here
                    // the endpoint is mapped in WebMVCConfig
                    .defaultSuccessUrl("/login_success")
                    // if login failed
                    // replace default /login?error
                    // the endpoint is mapped in WebMVCConfig
                    .failureUrl("/login_error")



                    /**
                     * Level 2
                     */
                    // if login successfully
                    // execute some extra code
                    // the endpoint is mapped in @Controller
                    // will override .defaultSuccessUrl
                    .successForwardUrl("/login_success_forward")
                    // if login failed
                    // execute some extra code
                    // the endpoint is mapped in @Controller
                    // will override .failureUrl
                    .failureForwardUrl("/login_error_forward")




                    /**
                     * Level 3
                     */
                    .successHandler(new AuthenticationSuccessHandler() {

                        @Override
                        public void onAuthenticationSuccess(HttpServletRequest request,
                                                            HttpServletResponse response,
                                                            Authentication authentication) throws IOException, ServletException {

                            System.out.println("[shark] Logged user: " + authentication.getName());

                            response.sendRedirect("/login_success_handler");
                        }
                    })

                    .failureHandler(new AuthenticationFailureHandler() {

                        @Override
                        public void onAuthenticationFailure(HttpServletRequest request,
                                                            HttpServletResponse response,
                                                            AuthenticationException exception) throws IOException, ServletException {
                            System.out.println("[shark] Login failed");
                            System.out.println(exception);

                            // needs to open visit
                            response.sendRedirect("/login_error_handler");
                        }
                    })




                /**
                 * Logout
                 */
                .and()
                    // open the /logout page
                    // GET /logout
                    // otherwise you will see error when you type /logout in the browser
                    .logout()
                    // By default, the logout endpoint is /logout
                    // customized this endpoint
                    .logoutUrl("/logout_my")
                    // Default is login page
                    // needs to be opened, otherwise you need to login
                    /**
                     * Level 1
                     */
                    .logoutSuccessUrl("/logout_success")
                    /**
                     * Level 2
                     */
                    .logoutSuccessHandler(new LogoutSuccessHandler() {
                        @Override
                        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                                    Authentication authentication)
                                throws IOException, ServletException {

                            System.out.println("[shark] This user logged out: " + authentication.getName());

                            response.sendRedirect("/logout_success_handler");
                        }
                    })
            ;


               ;



    }







    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER");
    }



}
