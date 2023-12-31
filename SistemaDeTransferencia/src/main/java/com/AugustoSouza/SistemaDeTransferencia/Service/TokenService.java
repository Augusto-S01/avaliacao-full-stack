package com.AugustoSouza.SistemaDeTransferencia.Service;

import org.springframework.stereotype.Service;

import com.AugustoSouza.SistemaDeTransferencia.Entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {
    


    public String generateToken(User user){
        try{
            Algorithm algo = Algorithm.HMAC256("secret");
            String token = com.auth0.jwt.JWT.create()
                    .withIssuer("SistemaDeTransferencia")
                    .withSubject(user.getUsername())
                    .sign(algo);
            return token;
        }catch(JWTCreationException e){
            throw new Error("Error creating token");
        }
    }


    public String validateToken(String token){
        try{
            Algorithm algo = Algorithm.HMAC256("secret");
            String subject = JWT.require(algo).withIssuer("SistemaDeTransferencia").build().verify(token).getSubject();
            return subject;
            
        }catch(JWTVerificationException e ){
            return "";
        }
       
    }
}
