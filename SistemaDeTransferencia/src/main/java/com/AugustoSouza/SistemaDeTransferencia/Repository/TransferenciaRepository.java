package com.AugustoSouza.SistemaDeTransferencia.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AugustoSouza.SistemaDeTransferencia.Entity.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    @Query("SELECT t FROM Transferencia t WHERE t.destinatario.accountNumber = :accountNumber OR t.remetente.accountNumber = :accountNumber")
    List<Transferencia> findByAccountNumber(@Param("accountNumber") Integer accountNumber);

    @Query("SELECT t FROM Transferencia t WHERE t.destinatario.username = :username OR t.remetente.username = :username")
    List<Transferencia> findByUsername(@Param("username") String username);
}
