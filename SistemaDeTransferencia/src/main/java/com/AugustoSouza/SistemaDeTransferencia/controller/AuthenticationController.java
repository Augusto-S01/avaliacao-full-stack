package com.AugustoSouza.SistemaDeTransferencia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AugustoSouza.SistemaDeTransferencia.DTO.AuthenticationdDTO;
import com.AugustoSouza.SistemaDeTransferencia.Entity.User;
import com.AugustoSouza.SistemaDeTransferencia.Repository.UserRepository;
import com.AugustoSouza.SistemaDeTransferencia.Service.TokenService;

import ch.qos.logback.core.subst.Token;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    
    private final AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationdDTO authDTO) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);

        String token = tokenService.generateToken((User) authenticate.getPrincipal());


        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated AuthenticationdDTO authenticationDTO) {
        if(userRepository.findByUsername(authenticationDTO.getUsername()) != null){
            return ResponseEntity.badRequest().build();
        }

        String password = new BCryptPasswordEncoder().encode(authenticationDTO.getPassword());
        User user = new User(authenticationDTO.getUsername(), password);


        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
    
    
}
