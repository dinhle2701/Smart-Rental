package com.project.SmartRental.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Tắt CSRF vì dùng JWT
                .csrf(AbstractHttpConfigurer::disable)
                // Stateless session vì JWT không cần lưu session
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Cấu hình quyền truy cập
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/contract").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/api/v1/vehicle").hasRole("ADMIN")
                .requestMatchers("/api/hostel").hasRole("ADMIN")
                .requestMatchers("/api/v1/tenant").hasRole("ADMIN")
                .requestMatchers("/api/v1/revenue").hasRole("ADMIN")
                .requestMatchers("/api/service").hasRole("ADMIN")
                .requestMatchers("/swagger-ui/index.html").permitAll()
                .requestMatchers("/api/v1/roomStage").permitAll()
                .requestMatchers("/api/v1/contractStage").permitAll()
                //                        .anyRequest().authenticated()
                .anyRequest().permitAll()
                )
                // Thêm filter JWT vào chuỗi xử lý
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
