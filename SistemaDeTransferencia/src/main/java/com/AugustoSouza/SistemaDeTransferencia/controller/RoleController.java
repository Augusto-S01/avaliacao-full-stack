package com.AugustoSouza.SistemaDeTransferencia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AugustoSouza.SistemaDeTransferencia.Entity.Role;
import com.AugustoSouza.SistemaDeTransferencia.Service.RoleService;

import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor
@RestController
@RequestMapping("/role")
public class RoleController {
    

    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    public Role create(@RequestBody Role role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return roleService.create(role);
    }
}
