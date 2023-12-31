package com.AugustoSouza.SistemaDeTransferencia.Filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.AugustoSouza.SistemaDeTransferencia.Exceptions.TokenNotFoundException;
import com.AugustoSouza.SistemaDeTransferencia.Repository.UserRepository;
import com.AugustoSouza.SistemaDeTransferencia.Service.TokenService;

import ch.qos.logback.core.subst.Token;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter  extends OncePerRequestFilter{


    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                
                String token = this.recoverToken(request);
                    if(token != null) {
                    String validateToken = tokenService.validateToken(token);
                    UserDetails user = userRepository.findByUsername(validateToken);
                    
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,null,null);   
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                }else{
                    throw new TokenNotFoundException("Missing token indentification");
                }
                filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null) {
            throw new RuntimeException("Token not found");
        }
        return authHeader.replace("Bearer ", "");
    }   
    
}
