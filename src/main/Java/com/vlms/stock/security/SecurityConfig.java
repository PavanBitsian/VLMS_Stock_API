package com.vlms.stock.security;

//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;


public class SecurityConfig {

//    //@Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        // InMemoryUserDetailsManager setup with two users
//        UserDetails admin = User.withUsername("vlmsapi")
//                .password(encoder.encode("ttkprestige"))
//                .roles("ADMIN", "USER")
//                .build();
//
//        UserDetails user = User.withUsername("vlmsttk")
//                .password(encoder.encode("hawkins"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }
//    //@Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//                http
//                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/products/**").permitAll() // Permit all access to /auth/welcome
//                        //.requestMatchers("/products/**").authenticated() // Require authentication for /auth/user/**
//
//                )
//                .formLogin(Customizer.withDefaults()); // Enable form-based login
////        http
////                .authorizeRequests()
////                .anyRequest().permitAll() // Permit all requests
////                .and()
////                .csrf().disable(); //
//        return http.build();
//    }
//
//    // Password Encoding
//    //@Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
