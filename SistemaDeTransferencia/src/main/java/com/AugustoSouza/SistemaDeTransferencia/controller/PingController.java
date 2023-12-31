package com.AugustoSouza.SistemaDeTransferencia.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
@RestController
@RequestMapping("/ping")
public class PingController {

    
    @RequestMapping("/pong")
    public String ping(@RequestHeader("headerName") String headerValue){
        return "pong";
    }

    @PostMapping("bang")
    public String bang() {
        return "bang";
    }
    

    
}
