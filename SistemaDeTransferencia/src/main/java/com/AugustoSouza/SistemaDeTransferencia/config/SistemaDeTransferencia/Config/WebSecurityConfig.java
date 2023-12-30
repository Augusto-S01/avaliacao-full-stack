package com.AugustoSouza.SistemaDeTransferencia.config.SistemaDeTransferencia.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final CustomBasicAuthenticationFilter customBasicAuthenticationFilter;




    @Bean
    public SecurityFilterChain filterCHain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> {
            csrf.disable();

        })
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests((request) -> request
            .requestMatchers(HttpMethod.GET, "/ping").permitAll()
            .requestMatchers(HttpMethod.POST, "/user/create").permitAll()
            .requestMatchers(HttpMethod.POST, "/role/create").permitAll()
            
            .anyRequest().authenticated())
            .addFilterBefore(customBasicAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    
        
            return http.build();


    }


}
