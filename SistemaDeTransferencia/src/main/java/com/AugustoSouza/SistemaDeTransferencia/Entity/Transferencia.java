package com.AugustoSouza.SistemaDeTransferencia.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.AugustoSouza.SistemaDeTransferencia.ENUM.StatusTransferencia;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TRANSFERENCIA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transferencia {
    

    public Transferencia(User destinatario, User remetente, BigDecimal valor, LocalDate dataTransferencia, BigDecimal taxa) {
        this.destinatario = destinatario;
        this.remetente = remetente;
        this.valor = valor;
        this.dataTransferencia = dataTransferencia;
        this.dataAgendamento = LocalDate.now();
        this.taxa = taxa;
        this.status = status.AGENDADA;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private User destinatario;
    @ManyToOne
    private User remetente;

    private BigDecimal valor;

    private LocalDate dataTransferencia;

    private LocalDate dataAgendamento;

    private BigDecimal taxa;
    @Enumerated(EnumType.STRING)
    private StatusTransferencia status;
}
