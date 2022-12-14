package com.Zera57.Application.Account;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService implements UserDetailsService {

    private final static String ACCOUNT_NOT_FOUND =
            "user with login %s not found";
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return accountRepository.findByEmail(email).orElseThrow(()
                -> new UsernameNotFoundException(ACCOUNT_NOT_FOUND));
    }

    public String signUpAccount(Account newAccount) {
        boolean accountExists = accountRepository.findByEmail(newAccount.getEmail()).isPresent();

        if (accountExists) {
            throw new IllegalStateException("login already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(newAccount.getPassword());

        newAccount.setPassword(encodedPassword);

        accountRepository.save(newAccount);

        return "success";
    }

    public int enableUser(String email) {
        return accountRepository.enableAccount(email);
    }
}
