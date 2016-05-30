package com.lsoftware.testing.service;

import com.lsoftware.testing.model.Client;
import com.lsoftware.testing.repository.ClientRepository;
import com.lsoftware.testing.service.exception.ClientNameAlreadyExistsException;
import com.lsoftware.testing.service.exception.InvalidClientNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createClient(String name) {
        if(StringUtils.isEmpty(name)) {
            throw new InvalidClientNameException();
        }

        if (clientRepository.findByName(name).isPresent()) {
            throw new ClientNameAlreadyExistsException();
        }

        return clientRepository.save(new Client(name));
    }
}
