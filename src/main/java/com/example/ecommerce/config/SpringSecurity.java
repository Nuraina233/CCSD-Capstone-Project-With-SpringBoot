package com.example.ecommerce.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
        http.csrf(Customizer.withDefaults())
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/**").permitAll()
                                .requestMatchers("/index").permitAll()
<<<<<<< Updated upstream
                                .requestMatchers("/cart").permitAll()
                                .requestMatchers("/customer/1").permitAll()
                                .requestMatchers("/customers/edit/1").permitAll()
                                .requestMatchers("/customers/{customer_id}").permitAll()
                                .requestMatchers("/product_page").permitAll()
                                .requestMatchers("/users").hasRole("ADMIN")
=======
                                .requestMatchers("/home").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
>>>>>>> Stashed changes
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
<<<<<<< Updated upstream
                                .defaultSuccessUrl("/customer/1")
=======
                                .defaultSuccessUrl("/admin")
>>>>>>> Stashed changes
                                .permitAll()
                ).logout(
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
