package com.AugustoSouza.SistemaDeTransferencia.DTO;

import java.time.Instant;

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
public class ErrorDTO {
    private String message;
    private int status;
    private Instant timestamp = Instant.now();

    public ErrorDTO(String message, int status) {
        this.message = message;
        this.status = status;
    }
    

}
