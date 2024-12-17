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

    // Register a new account
    public Account registerAccount(Account account) {
        // Check if username is blank
        if (account.getUsername() == null || account.getUsername().trim().isEmpty()) {
            return null;
        }

        // Check password length
        if (account.getPassword() == null || account.getPassword().length() < 4) {
            return null;
        }

        // Check for existing username
        if (accountRepository.findByUsername(account.getUsername()) != null) {
            return null;
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