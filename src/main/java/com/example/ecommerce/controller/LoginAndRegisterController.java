package com.example.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginAndRegisterController {
    @GetMapping("/loginRegister")
    public String loginRegister(){

        return "login-register";
    }
}
