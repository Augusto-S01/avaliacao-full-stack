package com.AugustoSouza.SistemaDeTransferencia.Repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import com.AugustoSouza.SistemaDeTransferencia.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
   UserDetails findByUsername(String username);

   @Query(value = "SELECT balance FROM \"user\" WHERE username = ?1", nativeQuery = true)
   BigDecimal findBalanceByUsername(String username);

   @Query(value = "SELECT balance FROM \"user\" WHERE account_number = ?1", nativeQuery = true)
   BigDecimal findBalanceByAccountNumber(Integer accountNumber);

   @Query(value = "SELECT * FROM \"user\" WHERE account_number = ?1", nativeQuery = true)
   User findByAccountNumber(Integer newAccountNumber);





  
}
