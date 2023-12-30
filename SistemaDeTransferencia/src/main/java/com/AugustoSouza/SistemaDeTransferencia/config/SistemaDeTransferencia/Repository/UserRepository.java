package com.AugustoSouza.SistemaDeTransferencia.config.SistemaDeTransferencia.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AugustoSouza.SistemaDeTransferencia.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);


    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = :username")
    User findByUsernameFetchRoles(@Param("username") String username);
}
