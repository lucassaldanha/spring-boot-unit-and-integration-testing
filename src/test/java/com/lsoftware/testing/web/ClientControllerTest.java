package com.lsoftware.testing.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsoftware.testing.model.Client;
import com.lsoftware.testing.service.ClientService;
import com.lsoftware.testing.service.exception.ClientNameAlreadyExistsException;
import com.lsoftware.testing.web.to.CreateClientRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ClientService clientServiceMock;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testCreateClientSuccessfully() throws Exception {
        given(clientServiceMock.createClient("Foo")).willReturn(new Client("Foo"));

        mockMvc.perform(post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new CreateClientRequest("Foo"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Foo")))
                .andExpect(jsonPath("$.number", notNullValue()));
    }

    @Test
    public void testCreateClientWithEmptyName() throws Exception {
        mockMvc.perform(post("/clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(new CreateClientRequest(""))))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateClientWithExistingName() throws Exception {
        given(clientServiceMock.createClient("Foo")).willThrow(new ClientNameAlreadyExistsException());

        mockMvc.perform(post("/clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(new CreateClientRequest("Foo"))))
            .andExpect(status().isConflict());
    }
}
