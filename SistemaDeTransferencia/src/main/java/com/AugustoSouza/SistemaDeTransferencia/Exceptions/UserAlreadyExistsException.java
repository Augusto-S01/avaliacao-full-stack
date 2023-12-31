package com.AugustoSouza.SistemaDeTransferencia.Exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class UserAlreadyExistsException extends  RuntimeException {
    
    public UserAlreadyExistsException(String message){
        super(message);
    }
}

