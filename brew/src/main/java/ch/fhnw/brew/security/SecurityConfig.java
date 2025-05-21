package ch.fhnw.brew.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService users() {
        return new InMemoryUserDetailsManager(
            User.withUsername("myuser")
                .password("{bcrypt}$2a$10$9fxQtdWuRaYn5UchAm5iAexbPi7tmRadnDogJwXPR9fVDJyt9g/su")
                .authorities("READ", "ROLE_USER")
                .build(),
            User.withUsername("myadmin")
                .password("{bcrypt}$2a$10$9fxQtdWuRaYn5UchAm5iAexbPi7tmRadnDogJwXPR9fVDJyt9g/su")
                .authorities("READ", "ROLE_ADMIN")
                .build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/login",                   
                    "/h2-console/**",           
                    "/swagger-ui.html",         
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                ).permitAll()
                .anyRequest().authenticated()   
            )
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
            .formLogin(form -> form
                .defaultSuccessUrl("/", true)   
            )
            .httpBasic(httpBasic -> {}) 
            .build();
    }
}
