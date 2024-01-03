package com.AugustoSouza.SistemaDeTransferencia.Service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.AugustoSouza.SistemaDeTransferencia.DTO.AuthenticationdDTO;
import com.AugustoSouza.SistemaDeTransferencia.DTO.EncontrarUsuarioDTO;
import com.AugustoSouza.SistemaDeTransferencia.DTO.UserDTO;
import com.AugustoSouza.SistemaDeTransferencia.Entity.User;
import com.AugustoSouza.SistemaDeTransferencia.Exceptions.UserAlreadyExistsException;
import com.AugustoSouza.SistemaDeTransferencia.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    public User usernameByAutehntication(AuthenticationdDTO authDTO) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                authDTO.getUsername(), authDTO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);
        return (User) authenticate.getPrincipal();
    }

    public UserDTO getUsuarioLogado(String username) {
        UserDetails teste = userRepository.findByUsername(username);
        UserDTO userDTO = new UserDTO(teste);
        
        return userDTO;
    }

    public ResponseEntity verificaUsuarioByAccountNumber(Integer accountNumber) {
        User user = userRepository.findByAccountNumber(accountNumber);
        if (user != null) {
            EncontrarUsuarioDTO userDTO = new EncontrarUsuarioDTO(user);
            return ResponseEntity.ok(userDTO);
        }
        EncontrarUsuarioDTO userDTO = new EncontrarUsuarioDTO("Usuario não encontrado");
        return ResponseEntity.ok(userDTO);
    }

    public ResponseEntity register(AuthenticationdDTO authenticationDTO) {
        if(userRepository.findByUsername(authenticationDTO.getUsername()) != null){
           throw new UserAlreadyExistsException("User already exists");
        }
        Integer generatedAccountNumber = generatedAccountNumber();
        String password = new BCryptPasswordEncoder().encode(authenticationDTO.getPassword());
        User user = new User(authenticationDTO.getUsername(), password,generatedAccountNumber);
        User save = userRepository.save(user);
        UserDTO userDTO = new UserDTO(save);
        return ResponseEntity.ok(userDTO);
    }


    private boolean isUniqueAccountNumber(Integer accountNumber) {
        User usuario = userRepository.findByAccountNumber(accountNumber);
        if(usuario == null){
            return true;
        }
        return false;
    }

    private Integer generatedAccountNumber(){
        Random random = new Random();
        Integer generatedAccountNumber;
        do {
            generatedAccountNumber = random.nextInt((999999 - 100000) + 1) + 100000;
        } while (!isUniqueAccountNumber(generatedAccountNumber));
        return generatedAccountNumber;
    }

}
