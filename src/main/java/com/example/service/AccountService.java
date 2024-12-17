package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Find account by username
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    // Register a new account
    public Account registerAccount(Account account) {
        // Check if username is blank or null
        if (account.getUsername() == null || account.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }

        // Check password length
        if (account.getPassword() == null || account.getPassword().length() < 4) {
            throw new IllegalArgumentException("Password must be at least 4 characters long");
        }

        // Check for existing username
        Account existingAccount = accountRepository.findByUsername(account.getUsername());
        if (existingAccount != null) {
            return null;  // This will trigger the 409 Conflict status
        }

        // Save and return the new account
        return accountRepository.save(account);
    }

    // Validate login credentials
    public Account login(Account account) {
        // Find account by username
        Account existingAccount = accountRepository.findByUsername(account.getUsername());

        // Check if account exists and password matches
        if (existingAccount != null && existingAccount.getPassword().equals(account.getPassword())) {
            return existingAccount;
        }

        return null;
    }
}