package com.lsoftware.testing.service;

import com.lsoftware.testing.domain.model.Account;
import com.lsoftware.testing.domain.model.Client;
import com.lsoftware.testing.domain.repository.AccountRepository;
import com.lsoftware.testing.domain.repository.ClientRepository;
import com.lsoftware.testing.service.exception.InvalidAccountCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private ClientRepository clientRepository;
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(ClientRepository clientRepository, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    public Account createAccount(String clientNumber) {
        Client client = clientRepository.findByNumber(clientNumber)
            .orElse(clientRepository.save(new Client(clientNumber)));

        if (accountRepository.findByClient(client).isPresent()) {
            throw new InvalidAccountCreationException("Client " + client.getNumber() + " already has an account");
        }

        return accountRepository.save(new Account(client));
    }

}
