package com.AugustoSouza.SistemaDeTransferencia.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationdDTO {

    private String username;
    private String password;
}
