package com.example.ecommerce.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String handleError() {
        // Handle the error and return the appropriate view or redirect
        return "error"; // For example, return the error page template name
    }

    public String getErrorPath() {
        return PATH;
    }
}
