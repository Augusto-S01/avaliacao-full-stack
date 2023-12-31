package com.AugustoSouza.SistemaDeTransferencia.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.AugustoSouza.SistemaDeTransferencia.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
   UserDetails findByUsername(String username);

}
