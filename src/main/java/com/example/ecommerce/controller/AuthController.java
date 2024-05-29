package com.example.ecommerce.controller;

import jakarta.validation.Valid;
import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index"; // Return the view name for the home page
    }

    // Handler method to handle login request
    @GetMapping("/login")
    public String login(){

        OrderController.productsList.clear();

        return "login"; // Return the view name for the login page
    }

    // Handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // Create a model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user); // Add user object to the model
        return "register"; // Return the view name for the registration form
    }

    // Handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        // Check if user with the same email already exists
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        // If user already exists, reject the registration with an error message
        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        // If there are validation errors, return to the registration form with the error messages
        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        // Save the user if there are no errors and redirect to the registration page with success message
        userService.saveUser(userDto);
        return "redirect:/login?success";
    }

    // Handler method to handle list of users
    @GetMapping("/users")
    public String users(Model model){
        // Retrieve list of users from the service
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users); // Add users list to the model
        return "users"; // Return the view name for the users list page
    }
}
