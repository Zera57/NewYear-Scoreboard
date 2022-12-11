package com.Zera57.Application.Account;

import com.Zera57.Application.Registration.token.ConfirmationToken;
import com.Zera57.Application.Registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountService implements UserDetailsService {

    private final static String ACCOUNT_NOT_FOUND =
            "user with email %s not found";
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return accountRepository.findByEmail(email).orElseThrow(()
                -> new UsernameNotFoundException(ACCOUNT_NOT_FOUND));
    }

    public String signUpAccount(Account newAccount) {
        boolean accountExists = accountRepository.findByEmail(newAccount.getEmail()).isPresent();

        if (accountExists) {
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(newAccount.getPassword());

        newAccount.setPassword(encodedPassword);

        accountRepository.save(newAccount);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                newAccount
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public int enableUser(String email) {
        return accountRepository.enableAccount(email);
    }
}
