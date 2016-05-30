package com.lsoftware.testing.web;

import com.lsoftware.testing.model.Client;
import com.lsoftware.testing.service.ClientService;
import com.lsoftware.testing.service.exception.ClientNameAlreadyExistsException;
import com.lsoftware.testing.web.to.CreateClientRequest;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Client> createClient(@RequestBody @Valid CreateClientRequest request) {
        Client client = clientService.createClient(request.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @ExceptionHandler(ClientNameAlreadyExistsException.class)
    ResponseEntity handleClientServiceException(Throwable ex) {
        if(ex instanceof ClientNameAlreadyExistsException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
}
