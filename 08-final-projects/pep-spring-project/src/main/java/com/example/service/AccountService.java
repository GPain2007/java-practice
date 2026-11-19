package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(Integer id) {
        return accountRepository.findById(id);
    }

    public Optional<Account> getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public Account createAccount(String username, String password) {
       Account account = new Account(username, password);
        return accountRepository.save(account);
    }

    public boolean isLoginValid(Account account) {
        Optional<Account> storedAccount = accountRepository.findByUsername(account.getUsername());
        return storedAccount.filter(a -> a.getPassword().equals(account.getPassword())).isPresent();
    }
}
