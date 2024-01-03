package com.AugustoSouza.SistemaDeTransferencia.Entity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.AugustoSouza.SistemaDeTransferencia.ENUM.UserRole;
import com.AugustoSouza.SistemaDeTransferencia.Repository.UserRepository;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "\"USER\"")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {



    private static final long serialVersionUID = 1L;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = new BigDecimal(5000);
    }
    public User(String username, String password, Integer accountNumber) {
        this.username = username;
        this.password = password;
        this.accountNumber = accountNumber;
        this.balance = new BigDecimal(5000);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    private Integer accountNumber;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @NotNull
    private BigDecimal balance;

    private UserRole role;

    @OneToMany(mappedBy = "destinatario")
    private List<Transferencia> transferenciasAsDestinatario;
 
    @OneToMany(mappedBy = "remetente")
    private List<Transferencia> transferenciasAsRemetente;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == role.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
