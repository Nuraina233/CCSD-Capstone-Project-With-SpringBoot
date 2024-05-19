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

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(Customizer.withDefaults())
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers("/**").permitAll()
                                .requestMatchers("/register/**").permitAll() // Allow access to registration page
                                .requestMatchers("/index").permitAll() // Allow access to index page
                                .requestMatchers("/cart").permitAll() // Allow access to cart page

                                .requestMatchers("/admin/**").hasRole("ADMIN") // Require ADMIN role for /adminDashboard endpoint
                )
                .formLogin(
                        form -> form
                                .loginPage("/login") // Custom login page URL
                                .loginProcessingUrl("/login") // URL for processing login form
                                .successHandler((request, response, authentication) -> {
                                    // Retrieve authorities of the authenticated user
                                    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                                    System.out.println("Authorities: " + authorities);

                                    // Check if the user has ADMIN role
                                    boolean isAdmin = authorities.stream()
                                            .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

                                    // Redirect based on user's role
                                    if (isAdmin) {
                                        response.sendRedirect("/admin"); // Redirect admin to adminDashboard
                                    } else {
                                        response.sendRedirect("/home"); // Redirect other users to index
                                    }
                                })
                                .permitAll() // Allow access to login page
                )
                .logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
