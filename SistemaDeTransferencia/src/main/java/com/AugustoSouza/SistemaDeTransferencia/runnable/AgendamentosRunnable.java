package com.AugustoSouza.SistemaDeTransferencia.runnable;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.AugustoSouza.SistemaDeTransferencia.ENUM.StatusTransferencia;
import com.AugustoSouza.SistemaDeTransferencia.Entity.Transferencia;
import com.AugustoSouza.SistemaDeTransferencia.Entity.User;
import com.AugustoSouza.SistemaDeTransferencia.Repository.TransferenciaRepository;
import com.AugustoSouza.SistemaDeTransferencia.Repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentosRunnable implements Runnable {


    private UserRepository userRepository;


    private TransferenciaRepository transferenciaRepository;

    private Transferencia transferencia;

    public AgendamentosRunnable(Transferencia transferencia, UserRepository userRepository, TransferenciaRepository transferenciaRepository) {
        this.transferencia = transferencia;
        this.userRepository = userRepository;
        this.transferenciaRepository = transferenciaRepository;
    }

    @Override
    public void run() {
        User destinatario = transferencia.getDestinatario();
        BigDecimal bigDecimal = destinatario.getBalance().add(transferencia.getValor());
        destinatario.setBalance(bigDecimal);
        transferencia.setStatus(StatusTransferencia.REALIZADA);
        userRepository.save(destinatario);
        transferenciaRepository.save(transferencia);
    }

}
