package com.AugustoSouza.SistemaDeTransferencia.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.AugustoSouza.SistemaDeTransferencia.DTO.HistoricoTransferenciaDTO;
import com.AugustoSouza.SistemaDeTransferencia.DTO.TransferenciaPayloadDTO;
import com.AugustoSouza.SistemaDeTransferencia.DTO.UserDTO;
import com.AugustoSouza.SistemaDeTransferencia.ENUM.StatusTransferencia;
import com.AugustoSouza.SistemaDeTransferencia.Entity.Transferencia;
import com.AugustoSouza.SistemaDeTransferencia.Entity.User;
import com.AugustoSouza.SistemaDeTransferencia.Repository.TransferenciaRepository;
import com.AugustoSouza.SistemaDeTransferencia.Repository.UserRepository;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AgendamentoService agendamentoService;

    public ResponseEntity agendarTransferencia(TransferenciaPayloadDTO transDTO, Principal principal) {
        User destinatario = userRepository.findByAccountNumber(transDTO.getDestinatarioAccountNumber());
        User remetente = (User) userRepository.findByUsername(principal.getName());
        BigDecimal taxa = calcularTaxa(transDTO.getValor(), transDTO.getDataTransferencia());
        

        

        if (!remetenteTemSaldo(transDTO, taxa, principal))
             // TODO melhorar a resposta desse retorno
            return ResponseEntity.badRequest().body("Saldo insuficiente");

        remetente.setBalance(remetente.getBalance().subtract(transDTO.getValor().add(taxa)));

        Transferencia transferencia = new Transferencia(
                destinatario,
                remetente,
                transDTO.getValor(),
                transDTO.getDataTransferencia(),
                taxa);
        Transferencia save = transferenciaRepository.save(transferencia);
        userRepository.save(remetente);
        agendamentoService.agendarTrasferencia(save);

        // TODO melhorar a resposta desse retorno
        return ResponseEntity.ok().body("Transferencia agendada com sucesso");
    }

    public ResponseEntity historicoTransferencias(Principal principal) {
        List<Transferencia> transferencias = transferenciaRepository.findByUsername(principal.getName());
        if(transferencias.isEmpty())
        //TODO MELHORAR a resposta desse retorno
            return ResponseEntity.badRequest().body("Nenhuma transferencia encontrada");

        HistoricoTransferenciaDTO historicoTransferenciaDTO = new HistoricoTransferenciaDTO();
        List<HistoricoTransferenciaDTO> teste = transferencias.stream().map(transferencia -> new HistoricoTransferenciaDTO(transferencia)).collect(Collectors.toList());
        

        return ResponseEntity.ok().body(teste);
    }


    private BigDecimal calcularTaxa(BigDecimal quantidade, LocalDate dataAgendada) {
        boolean taxaA = false;
        boolean taxaB = false;
        boolean taxaC = false;
        BigDecimal taxaCVal = BigDecimal.ZERO;
        BigDecimal taxa = BigDecimal.ZERO;

        // quantidade
        if (quantidade.compareTo(new BigDecimal("1000")) <= 0) {
            taxaA = true;
        } else if (quantidade.compareTo(new BigDecimal("1000")) > 0
                && quantidade.compareTo(new BigDecimal("2000")) <= 0) {
            taxaB = true;
        } else if (quantidade.compareTo(new BigDecimal("2000")) > 0) {
            taxaC = true;
        }

        // tempo
        LocalDate dataAtual = LocalDate.now();
        if (dataAtual.equals(dataAgendada)) {
            taxaA = true;
        } else if (menosQue10Dias(dataAgendada)) {
            taxaB = true;
        }

        if (entre10a20Dias(dataAgendada)) {
            taxaC = true;
            taxaCVal = new BigDecimal("0.082");
        } else if (entre20a30Dias(dataAgendada)) {
            taxaC = true;
            taxaCVal = new BigDecimal("0.069");
        } else if (entre30a40Dias(dataAgendada)) {
            taxaC = true;
            taxaCVal = new BigDecimal("0.054");
        } else if (ChronoUnit.DAYS.between(dataAtual, dataAgendada) > 40) {
            taxaC = true;
            taxaCVal = new BigDecimal("0.017");
        }

        // calculo
        if (taxaA) {
            taxa = taxa.add(new BigDecimal("3")).add((quantidade.multiply(new BigDecimal("0.03"))));
        }
        if (taxaB) {
            taxa = taxa.add(new BigDecimal("12"));
        }
        if (taxaC && !taxaCVal.equals(BigDecimal.ZERO)) {
            taxa = taxa.add(quantidade.multiply(taxaCVal));
        }
        return taxa;
    }

    private boolean menosQue10Dias(LocalDate dataAgendada) {
        LocalDate dataAtual = LocalDate.now();
        long dias = ChronoUnit.DAYS.between(dataAtual, dataAgendada);
        if (dias <= 10) {
            return true;
        }
        return false;
    }

    private boolean entre10a20Dias(LocalDate dataAgendada) {
        LocalDate dataAtual = LocalDate.now();
        long dias = ChronoUnit.DAYS.between(dataAtual, dataAgendada);
        if (dias > 10 && dias <= 20) {
            return true;
        }
        return false;
    }

    private boolean entre20a30Dias(LocalDate dataAgendada) {
        LocalDate dataAtual = LocalDate.now();
        long dias = ChronoUnit.DAYS.between(dataAtual, dataAgendada);
        if (dias > 20 && dias <= 30) {
            return true;
        }
        return false;
    }

    private boolean entre30a40Dias(LocalDate dataAgendada) {
        LocalDate dataAtual = LocalDate.now();
        long dias = ChronoUnit.DAYS.between(dataAtual, dataAgendada);
        if (dias > 30 && dias <= 40) {
            return true;
        }
        return false;
    }

    private boolean remetenteTemSaldo(TransferenciaPayloadDTO transDTO, BigDecimal taxa, Principal principal) {

        BigDecimal saldoRemetente = userRepository.findBalanceByUsername(principal.getName());

        BigDecimal valorTotal = transDTO.getValor().add(taxa);
        if (saldoRemetente.compareTo(valorTotal) >= 0) {
            return true;
        }
        return false;
    }





}
