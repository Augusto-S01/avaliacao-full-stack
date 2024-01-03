package com.AugustoSouza.SistemaDeTransferencia.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AugustoSouza.SistemaDeTransferencia.Service.UserService;

import lombok.NoArgsConstructor;

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
}
