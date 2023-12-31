package com.AugustoSouza.SistemaDeTransferencia.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.AugustoSouza.SistemaDeTransferencia.Entity.User;
import com.AugustoSouza.SistemaDeTransferencia.Repository.UserRepository;


@Service
public class UserService {
    

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserRepository userRepository;
    

    public User create(User user){

        // User existUser = userRepository.findByUsername(user.getUsername());
        User existUser = null;

        if(existUser != null){
            throw new Error("User aleady exists");
        }
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        User createUser = userRepository.save(user);

        return createUser;
    }
}
