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
import com.AugustoSouza.SistemaDeTransferencia.DTO.LoginResponseDTO;
import com.AugustoSouza.SistemaDeTransferencia.Entity.User;
import com.AugustoSouza.SistemaDeTransferencia.Repository.UserRepository;
import com.AugustoSouza.SistemaDeTransferencia.Service.TokenService;
import com.AugustoSouza.SistemaDeTransferencia.Service.UserService;

import ch.qos.logback.core.subst.Token;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    


    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationdDTO authDTO) {

        User user = userService.usernameByAutehntication(authDTO);
        String token = tokenService.generateToken(user);
        
        LoginResponseDTO loginDTO = new LoginResponseDTO(user.getUsername(), token);

        return ResponseEntity.ok(loginDTO);
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
