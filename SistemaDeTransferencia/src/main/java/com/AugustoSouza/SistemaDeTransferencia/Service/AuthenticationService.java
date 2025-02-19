package com.AugustoSouza.SistemaDeTransferencia.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.AugustoSouza.SistemaDeTransferencia.Repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserDetails user = userRepository.findByUsername(username);
            if (user == null)
                throw new UsernameNotFoundException("User not found");
            return user;
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }

    }

}
