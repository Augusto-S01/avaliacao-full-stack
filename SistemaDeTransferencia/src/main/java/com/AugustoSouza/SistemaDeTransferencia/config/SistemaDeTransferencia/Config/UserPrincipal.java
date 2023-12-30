package com.AugustoSouza.SistemaDeTransferencia.config.SistemaDeTransferencia.Config;

import java.util.Collection;
import java.util.stream.Collectors;

import com.AugustoSouza.SistemaDeTransferencia.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;


@Getter
public class UserPrincipal {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private UserPrincipal(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority("Role_" + role.getName())).collect(Collectors.toList());
    }

    public static UserPrincipal create(User user) {
        return new UserPrincipal(user);
    }

}
