package com.AugustoSouza.SistemaDeTransferencia.Service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AugustoSouza.SistemaDeTransferencia.Repository.UserRepository;

@Service
public class SaldoService {


    @Autowired
    private UserRepository userRepository;
    
    public BigDecimal getSaldo(String username){
       BigDecimal teste =userRepository.findBalanceByUsername(username);
        return teste;
    }

}
