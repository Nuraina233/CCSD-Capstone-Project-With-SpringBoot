package com.example.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonController {

    @GetMapping("people")
    String getPeople(Model model){
        model.addAttribute("something","this is coming from the controller");
        return "people";    //html file
    }
}
