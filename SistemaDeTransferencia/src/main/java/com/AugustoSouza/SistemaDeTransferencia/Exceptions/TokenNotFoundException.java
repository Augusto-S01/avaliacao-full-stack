package com.AugustoSouza.SistemaDeTransferencia.Exceptions;

public class TokenNotFoundException extends  RuntimeException{
    public TokenNotFoundException(String message){
        super(message);
    }
}
