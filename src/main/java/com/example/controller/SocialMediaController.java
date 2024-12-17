package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SocialMediaController {
    private final AccountService accountService;
    private final MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    // User registration
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Account account) {
        Account registeredAccount = accountService.registerAccount(account);
        
        if (registeredAccount != null) {
            return ResponseEntity.ok(registeredAccount);
        } else if (accountService.findByUsername(account.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // User login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        Account authenticatedAccount = accountService.login(account);
        
        if (authenticatedAccount != null) {
            return ResponseEntity.ok(authenticatedAccount);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }