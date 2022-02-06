package com.example.demo10;/**
 * Created by TUTEHUB on 2022/2/5 6:56 PM.
 * Copyright © 2022 TUTEHUB. All rights reserved.
 * ------------------------
 * Non-disclosure Terms
 * -------------------------
 * These codes are TUTEHUB's property, as the developer, such as employee, contractor, and intern, working for TUTEHUB cannot disclose, spread, copy, preserve, sell, and do other activities without the consent of TUTEHUB.
 * Developer dph agrees with above terms.
 * Technique Support: jobyme88.com
 * Contact: noreply@fengcaoculture.com
 */


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("index");
        registry.addViewController("/home").setViewName("home");

        // login
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/login_success").setViewName("login_success");
        registry.addViewController("/login_error").setViewName("login_error");


        registry.addViewController("/login_success_handler").setViewName("login_success_handler");
        registry.addViewController("/login_error_handler").setViewName("login_error_handler");





        // logout
        registry.addViewController("/logout_my").setViewName("logout_my");
        registry.addViewController("/logout_success").setViewName("logout_success");
        registry.addViewController("/logout_success_handler").setViewName("logout_success_handler");




    }


}
