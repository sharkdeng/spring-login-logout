package com.example.demo10;/**
 * Created by TUTEHUB on 2022/2/6 10:01 AM.
 * Copyright © 2022 TUTEHUB. All rights reserved.
 * ------------------------
 * Non-disclosure Terms
 * -------------------------
 * These codes are TUTEHUB's property, as the developer, such as employee, contractor, and intern, working for TUTEHUB cannot disclose, spread, copy, preserve, sell, and do other activities without the consent of TUTEHUB.
 * Developer dph agrees with above terms.
 * Technique Support: jobyme88.com
 * Contact: noreply@fengcaoculture.com
 */


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @description
 */
@Controller
public class WebController {

    @PostMapping("/login_success_forward")
    public String loginSuccessHandler() {
        System.out.println("[shark] Logging user login success...");
        return "login_success_forward";
    }



    @PostMapping("/login_error_forward")
    public String loginFailureHandler() {
        System.out.println("[shark] Login failure handler....");

        return "login_error_forward";
    }
}