package com.example.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    // Bean to provide PasswordEncoder for encrypting passwords
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Configuration for defining security rules and filter chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(Customizer.withDefaults())
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers("/**").permitAll()
                                .requestMatchers("/register/**").permitAll() // Allow access to registration page
                                .requestMatchers("/index").permitAll() // Allow access to index page
                                .requestMatchers("/cart").permitAll() // Allow access to cart page

                                .requestMatchers("/adminDashboard/**").hasRole("ADMIN") // Require ADMIN role for /adminDashboard endpoint
                )
                .formLogin(
                        form -> form
                                .loginPage("/login") // Custom login page URL
                                .loginProcessingUrl("/login") // URL for processing login form
                                .successHandler((request, response, authentication) -> {
                                    // Retrieve authorities of the authenticated user
                                    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

                                    // Check if the user has ADMIN role
                                    boolean isAdmin = authorities.stream()
                                            .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

                                    // Redirect based on user's role
                                    if (isAdmin) {
                                        response.sendRedirect("/adminDashboard"); // Redirect admin to adminDashboard
                                    } else {
                                        response.sendRedirect("/index"); // Redirect other users to index
                                    }
                                })
                                .permitAll() // Allow access to login page
                )
                .logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // URL for logout
                                .permitAll() // Allow access to logout URL
                );
        return http.build(); // Build and return the configured HttpSecurity object
    }


    // Configure global authentication manager to use UserDetailsService and PasswordEncoder
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService) // Set UserDetailsService for authentication
                .passwordEncoder(passwordEncoder()); // Set PasswordEncoder for password encoding
    }
}
