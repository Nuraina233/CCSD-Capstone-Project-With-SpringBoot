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
        http.csrf(Customizer.withDefaults()) // Configures CSRF protection with default settings
                .authorizeHttpRequests((authorize) -> // Configures request authorization
                        authorize.requestMatchers("/register/**").permitAll() // Permits all requests starting with "/register/"
                                .requestMatchers("/index").permitAll() // Permits requests to "/index"
                                .requestMatchers("/adminDashboard").hasRole("ADMIN") // Requires "ADMIN" role for requests to "/users"
                )
                .formLogin(form -> form // Configures form-based authentication
                        .loginPage("/login") // Specifies the custom login page URL
                        .loginProcessingUrl("/login") // Specifies the URL where the login form will be submitted
                        .successHandler((request, response, authentication) -> { // Custom success handler
                            for (GrantedAuthority auth : authentication.getAuthorities()) { // Loop through user's authorities (roles)
                                if (auth.getAuthority().equals("ROLE_ADMIN")) { // If user has "ROLE_ADMIN"
                                    response.sendRedirect("/adminDashboard"); // Redirects admins to admin page
                                    return;
                                }
                            }
                            // Redirects other users to homepage
                            response.sendRedirect("/");
                        })
                        .permitAll() // Permits access to the login page
                )
                .logout(logout -> logout // Configures logout behavior
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Specifies the URL to trigger logout
                        .permitAll() // Permits access to the logout URL
                );
        return http.build(); // Builds and returns the configured HttpSecurity object
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
