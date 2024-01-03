package com.AugustoSouza.SistemaDeTransferencia.DTO;

import java.math.BigDecimal;

import com.AugustoSouza.SistemaDeTransferencia.Entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EncontrarUsuarioDTO {
    
    private String username;
    private Integer accountNumber;
    private BigDecimal balance;
    private String error;

    public EncontrarUsuarioDTO(String error) {
        this.error = error;
    }

    public EncontrarUsuarioDTO(User user) {
        this.username = user.getUsername();
        this.accountNumber = user.getAccountNumber();
        this.balance = user.getBalance();
    }

}
