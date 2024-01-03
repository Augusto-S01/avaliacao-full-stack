package com.AugustoSouza.SistemaDeTransferencia.DTO;

import java.math.BigDecimal;

import org.springframework.security.core.userdetails.UserDetails;

import com.AugustoSouza.SistemaDeTransferencia.Entity.User;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {
    private String username;
    private Integer accountNumber;
    private BigDecimal  balance;


    public UserDTO(UserDetails userdetails) {
        this.accountNumber = ((User) userdetails).getAccountNumber();
        this.username = userdetails.getUsername();
        this.balance = ((User) userdetails).getBalance();
    }
}
