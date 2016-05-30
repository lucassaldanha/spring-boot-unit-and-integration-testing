package com.lsoftware.testing.service;

import com.lsoftware.testing.model.Client;
import com.lsoftware.testing.repository.ClientRepository;
import com.lsoftware.testing.service.exception.ClientNameAlreadyExistsException;
import com.lsoftware.testing.service.exception.InvalidClientNameException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class ClientServiceTest {

    private ClientService clientService;
    private ClientRepository clientRepositoryMock;

    @Before
    public void setUp() {
        clientRepositoryMock = Mockito.mock(ClientRepository.class);
        clientService = new ClientService(clientRepositoryMock);
    }

    @Test
    public void createClientSuccessfuly() throws Exception {
        when(clientRepositoryMock.findByName(eq("Foo"))).thenReturn(Optional.empty());
        doAnswer(returnsFirstArg()).when(clientRepositoryMock).save(any(Client.class));

        Client client = clientService.createClient("Foo");
        assertEquals("Foo", client.getName());
        assertNotNull(client.getNumber());
    }

    @Test(expected = InvalidClientNameException.class)
    public void createClientWithEmptyName() throws Exception {
        clientService.createClient("");
    }

    @Test(expected = ClientNameAlreadyExistsException.class)
    public void createClientWithExistingName() throws Exception {
        doThrow(new ClientNameAlreadyExistsException()).when(clientRepositoryMock).findByName(eq("Foo"));

        clientService.createClient("Foo");
    }
}