package com.AugustoSouza.SistemaDeTransferencia.config.SistemaDeTransferencia.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AugustoSouza.SistemaDeTransferencia.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
