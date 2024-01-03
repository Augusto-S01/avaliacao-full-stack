package com.AugustoSouza.SistemaDeTransferencia.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.AugustoSouza.SistemaDeTransferencia.ENUM.StatusTransferencia;
import com.AugustoSouza.SistemaDeTransferencia.Entity.Transferencia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoTransferenciaDTO {
    
    private UserDTO destinatario;
    private UserDTO remetente;
    private BigDecimal valor;
    private LocalDate dataTransferencia;
    private LocalDate dataAgendamento;
    private BigDecimal taxa;
    private StatusTransferencia status;

    public HistoricoTransferenciaDTO(Transferencia transferencia) {
        this.destinatario = new UserDTO(transferencia.getDestinatario());
        this.remetente = new UserDTO(transferencia.getRemetente());
        this.valor = transferencia.getValor();
        this.dataTransferencia = transferencia.getDataTransferencia();
        this.dataAgendamento = transferencia.getDataAgendamento();
        this.taxa = transferencia.getTaxa();
        this.status = transferencia.getStatus();
    }


    
}
