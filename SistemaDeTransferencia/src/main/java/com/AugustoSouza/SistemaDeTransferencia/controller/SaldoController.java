package com.AugustoSouza.SistemaDeTransferencia.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AugustoSouza.SistemaDeTransferencia.Service.SaldoService;

import lombok.NoArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@NoArgsConstructor
@RestController
@RequestMapping("/saldo")
@CrossOrigin("*")
public class SaldoController {
    

    @Autowired
    private SaldoService saldoService;

    @GetMapping("/getSaldo")
    public ResponseEntity getSaldo(Principal principal) {
        return ResponseEntity.ok(saldoService.getSaldo(principal.getName()));
    }
    
}
