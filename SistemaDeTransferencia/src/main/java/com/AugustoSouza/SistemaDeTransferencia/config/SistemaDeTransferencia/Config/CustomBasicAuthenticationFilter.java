package com.AugustoSouza.SistemaDeTransferencia.config.SistemaDeTransferencia.Config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.AugustoSouza.SistemaDeTransferencia.Entity.User;
import com.AugustoSouza.SistemaDeTransferencia.config.SistemaDeTransferencia.Repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.util.Base64;


@Component
// @RequiredArgsConstructor
public class CustomBasicAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    private UserRepository userRepository;

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if(isBasicAuthentication(request)){
            String[] credentials = new String(Base64.getDecoder().decode(getHeader(request).replace("Basic ", ""))).split(":");
        
            String username = credentials[0];
            String password = credentials[1];
            

            User user = userRepository.findByUsernameFetchRoles(username);

            if(user == null){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("User not found");
                return;
            }

            boolean valid = checkpassword(user.getPassword(), password);

            if(!valid){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid password");
                return;
            }
            setAuthentication(user);
            
            filterChain.doFilter(request, response);


        }else {
            filterChain.doFilter(request, response);
        }
    }

    private void setAuthentication(User user) {
        Authentication authentication = createAuthenticationToken(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Authentication createAuthenticationToken(User user) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        return new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
    }


    private boolean checkpassword(String userPassword, String logindPassword) {


        return passwordEncoder().matches(logindPassword, userPassword);
    }

    private boolean isBasicAuthentication(HttpServletRequest request) {
        String header = getHeader(request);
        return header != null && header.startsWith("Basic ");
    }

    private String getHeader(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }
    
}
