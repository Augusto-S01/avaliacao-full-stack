package com.AugustoSouza.SistemaDeTransferencia.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import com.AugustoSouza.SistemaDeTransferencia.Entity.Transferencia;
import com.AugustoSouza.SistemaDeTransferencia.Repository.TransferenciaRepository;
import com.AugustoSouza.SistemaDeTransferencia.Repository.UserRepository;
import com.AugustoSouza.SistemaDeTransferencia.runnable.AgendamentosRunnable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
// @NoArgsConstructor
public class AgendamentoService {

   
    private TaskScheduler scheduler;

    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private TransferenciaRepository transferenciaRepository;
    
    public void agendarTrasferencia(Transferencia transferencia) {
        Runnable task = new AgendamentosRunnable(transferencia, userRepository, transferenciaRepository);
        LocalDate date = transferencia.getDataTransferencia();
        LocalDate today = LocalDate.now();
        if (today.isEqual(date)) {
            task.run();
        } else {
            Date targetDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
            scheduler.schedule(task, new CronTrigger("0 0 0 * * ?"));
        }
     }
     
}
