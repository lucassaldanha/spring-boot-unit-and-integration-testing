package com.lsoftware.testing.web.controller;

import com.lsoftware.testing.domain.model.Client;
import com.lsoftware.testing.service.CreateClientService;
import com.lsoftware.testing.service.exception.ClientNameAlreadyExistsException;
import com.lsoftware.testing.web.to.CreateClientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    private CreateClientService createClientService;

    @Autowired
    public ClientController(CreateClientService createClientService) {
        this.createClientService = createClientService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Client> createClient(@RequestBody @Valid CreateClientRequest request) {
        Client client = createClientService.createClient(request.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @ExceptionHandler(ClientNameAlreadyExistsException.class)
    ResponseEntity handleClientServiceException(Throwable ex) {
        if(ex instanceof ClientNameAlreadyExistsException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    public void setCreateClientService(CreateClientService createClientService) {
        this.createClientService = createClientService;
    }
}
