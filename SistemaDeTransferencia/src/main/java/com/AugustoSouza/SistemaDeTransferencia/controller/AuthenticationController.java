package com.AugustoSouza.SistemaDeTransferencia.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AugustoSouza.SistemaDeTransferencia.DTO.AuthenticationdDTO;
import com.AugustoSouza.SistemaDeTransferencia.DTO.LoginResponseDTO;
import com.AugustoSouza.SistemaDeTransferencia.Entity.User;
import com.AugustoSouza.SistemaDeTransferencia.Exceptions.UserAlreadyExistsException;
import com.AugustoSouza.SistemaDeTransferencia.Repository.UserRepository;
import com.AugustoSouza.SistemaDeTransferencia.Service.TokenService;
import com.AugustoSouza.SistemaDeTransferencia.Service.UserService;

import ch.qos.logback.core.subst.Token;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {
    


    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login( @Valid  @RequestBody  AuthenticationdDTO authDTO) {

        User user = userService.usernameByAutehntication(authDTO);
        String token = tokenService.generateToken(user);

        LoginResponseDTO loginDTO = new LoginResponseDTO(user.getUsername(), token);
        return ResponseEntity.ok(loginDTO);
    }

    @PostMapping("/register")
    public ResponseEntity register( @Valid @RequestBody AuthenticationdDTO authenticationDTO) {
        if(userRepository.findByUsername(authenticationDTO.getUsername()) != null){
           throw new UserAlreadyExistsException("User already exists");
        }
        
        /////////////////////////////////
        Integer generatedAccountNumber;
        Random random = new Random();
            do {
            generatedAccountNumber = random.nextInt((999999 - 100000) + 1) + 100000;
        } while (!isUniqueAccountNumber(generatedAccountNumber));
        //////////////////////
        String password = new BCryptPasswordEncoder().encode(authenticationDTO.getPassword());
        User user = new User(authenticationDTO.getUsername(), password,generatedAccountNumber);


        User save = userRepository.save(user);
        return ResponseEntity.ok(save);
    }
    
     private boolean isUniqueAccountNumber(Integer accountNumber) {
        User usuario = userRepository.findByAccountNumber(accountNumber);
        if(usuario == null){
            return true;
        }
        return false;
    }
}


   
