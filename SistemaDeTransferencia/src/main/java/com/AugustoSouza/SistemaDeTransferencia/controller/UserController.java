package com.AugustoSouza.SistemaDeTransferencia.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AugustoSouza.SistemaDeTransferencia.Service.UserService;

import jakarta.websocket.server.PathParam;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@NoArgsConstructor
@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/getUsuarioLogado")
    public ResponseEntity getUsuarioLogado(Principal principal) {
        return ResponseEntity.ok(userService.getUsuarioLogado(principal.getName()));
    }

    @GetMapping("/verificaUsuario/{accountNumber}")
    public ResponseEntity getMethodName(@PathVariable Integer accountNumber) {
        return userService.verificaUsuarioByAccountNumber(accountNumber);
    }
    
}
