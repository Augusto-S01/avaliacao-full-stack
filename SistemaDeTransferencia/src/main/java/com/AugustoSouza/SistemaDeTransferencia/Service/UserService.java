package com.AugustoSouza.SistemaDeTransferencia.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.AugustoSouza.SistemaDeTransferencia.DTO.AuthenticationdDTO;
import com.AugustoSouza.SistemaDeTransferencia.Entity.User;
import com.AugustoSouza.SistemaDeTransferencia.Repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService {
    
    @Autowired

    private final AuthenticationManager authenticationManager;

    public User usernameByAutehntication(AuthenticationdDTO authDTO) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);
        return (User) authenticate.getPrincipal();
        
    }

}
