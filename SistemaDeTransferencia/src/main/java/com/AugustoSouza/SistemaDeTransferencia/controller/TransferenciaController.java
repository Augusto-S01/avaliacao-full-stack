package com.AugustoSouza.SistemaDeTransferencia.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AugustoSouza.SistemaDeTransferencia.DTO.TransferenciaPayloadDTO;
import com.AugustoSouza.SistemaDeTransferencia.Service.TransferenciaService;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@NoArgsConstructor
@RestController
@RequestMapping("/transferencia")
@CrossOrigin("*")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping("/agendarTransferencia")
    public ResponseEntity agendarTransferencia(@RequestBody TransferenciaPayloadDTO transDTO, Principal principal) {
        return transferenciaService.agendarTransferencia(transDTO, principal);
    }

    @GetMapping("/historicoTransferencias")
    public ResponseEntity getMethodName(Principal principal) {
        return transferenciaService.historicoTransferencias(principal);
    }

}
