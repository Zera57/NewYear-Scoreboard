package com.Zera57.Application.Account;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountService implements UserDetailsService {

    private final static String ACCOUNT_NOT_FOUND =
            "user with login %s not found";
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String name)
            throws UsernameNotFoundException {
        return accountRepository.findByName(name).orElseThrow(()
                -> new UsernameNotFoundException(ACCOUNT_NOT_FOUND));
    }

    public String signUpAccount(Account newAccount) {
        boolean accountExists = accountRepository.findByName(newAccount.getName()).isPresent();

        if (accountExists) {
            throw new IllegalStateException("login already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(newAccount.getPassword());

        newAccount.setPassword(encodedPassword);

        accountRepository.save(newAccount);

        return "success";
    }
}
