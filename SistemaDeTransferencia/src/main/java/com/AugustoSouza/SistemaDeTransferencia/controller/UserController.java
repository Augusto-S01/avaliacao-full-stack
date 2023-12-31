package com.AugustoSouza.SistemaDeTransferencia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AugustoSouza.SistemaDeTransferencia.Entity.User;
import com.AugustoSouza.SistemaDeTransferencia.Service.UserService;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public User create(@RequestBody User user){
        return userService.create(user);
    }



}
