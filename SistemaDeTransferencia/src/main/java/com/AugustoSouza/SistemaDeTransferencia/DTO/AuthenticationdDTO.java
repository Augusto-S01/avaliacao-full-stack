package com.AugustoSouza.SistemaDeTransferencia.DTO;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationdDTO {

    @NotBlank ( message = "username cannot be blank")
    @NotNull ( message = "Username cannot be null")
    private String username;
    @NotBlank (message = "Password cannot be blank")
    @NotNull (message = "Password cannot be null")
    @NotEmpty ( message = "Password cannot be empty")
    private String password;
}
