package com.AugustoSouza.SistemaDeTransferencia.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.AugustoSouza.SistemaDeTransferencia.Entity.Transferencia;
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
public class TransferenciaPayloadDTO {
    
    private String destinatarioUsername;
    private Integer destinatarioAccountNumber;
    private BigDecimal valor;
    private LocalDate dataTransferencia;




    public TransferenciaPayloadDTO(Transferencia transferencia) {
        this.destinatarioUsername = transferencia.getDestinatario().getUsername();
        this.destinatarioAccountNumber = transferencia.getDestinatario().getAccountNumber();
        this.valor = transferencia.getValor();
        this.dataTransferencia = transferencia.getDataTransferencia();
    }

}
