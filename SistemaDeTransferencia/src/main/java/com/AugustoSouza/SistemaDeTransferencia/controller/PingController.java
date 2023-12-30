package com.AugustoSouza.SistemaDeTransferencia.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/ping")
public class PingController {
    
    @RequestMapping("/pong")
    @PreAuthorize("hasRole('2')")
    public String ping(){
        return "pong";
    }

    @PostMapping("bang")
    @PreAuthorize("hasRole('1')")
    public String bang() {
        return "bang";
    }
    

    
}
