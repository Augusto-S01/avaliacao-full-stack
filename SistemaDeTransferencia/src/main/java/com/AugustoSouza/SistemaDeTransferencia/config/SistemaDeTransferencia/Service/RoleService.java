package com.AugustoSouza.SistemaDeTransferencia.config.SistemaDeTransferencia.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AugustoSouza.SistemaDeTransferencia.Entity.Role;
import com.AugustoSouza.SistemaDeTransferencia.config.SistemaDeTransferencia.Repository.RoleRepository;

@Service
public class RoleService {


    @Autowired
    private RoleRepository roleRepository;

    public Role create(Role role){
        return roleRepository.save(role);
    }
    
}
