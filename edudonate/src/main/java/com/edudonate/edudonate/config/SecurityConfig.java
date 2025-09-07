package com.edudonate.edudonate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // PasswordEncoder bean (used by UserService)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Security filter chain configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // ✅ Public pages (no login needed)
                        .requestMatchers("/", "/home", "/login", "/register", "/css/**", "/js/**").permitAll()

                        // ✅ Protected pages (only logged-in users can access)
                        .requestMatchers("/dashboard", "/donations/**", "/rent/**", "/exchange/**").authenticated()

                        // ✅ Any other request also requires login
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")                  // custom login page
                        .defaultSuccessUrl("/dashboard", true) // redirect after successful login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")   // after logout, go back to login
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable()); // disable CSRF for now (enable later if using tokens)

        return http.build();
    }
}
