package com.Zera57.Application.Registration;

import com.Zera57.Application.Account.Account;
import com.Zera57.Application.Account.AccountRole;
import com.Zera57.Application.Account.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AccountService accountService;

    public String register(RegistrationRequest request) {
        return accountService.signUpAccount(
                new Account(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AccountRole.USER
                )
        );
    }
}
