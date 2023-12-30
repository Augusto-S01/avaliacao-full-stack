package com.AugustoSouza.SistemaDeTransferencia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AugustoSouza.SistemaDeTransferencia.Entity.Role;
import com.AugustoSouza.SistemaDeTransferencia.config.SistemaDeTransferencia.Service.RoleService;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@RestController
@RequestMapping("/role")
public class RoleController {
    

    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    public Role create(@RequestBody Role role){
        return roleService.create(role);
    }
}
