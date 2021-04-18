package com.company.controller;

import com.company.dto.ErrorDTO;
import com.company.dto.UserDTO;
import com.company.model.User;
import com.company.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class UserControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void getUserById(){
        UserDTO userDTO = this.restTemplate.getForObject("http://localhost:"
                        + port + "/api/rest/User.svc/user(2)", UserDTO.class);
        assertEquals("user2", userDTO.getUsername());
        assertEquals("user2@user.com", userDTO.getEmail());
    }


    @Test
    void saveUser() throws JsonProcessingException {
        UserDTO userDTOtoInsert = new UserDTO();
        userDTOtoInsert.setEmail("any@thing.com");
        userDTOtoInsert.setAddress("Greece, Crete");
        userDTOtoInsert.setPersonalCode("101010");
        userDTOtoInsert.setUsername("eliza");

        HttpEntity<UserDTO> request = new HttpEntity<>(userDTOtoInsert);
        UserDTO user = this.restTemplate.postForObject("http://localhost:"
                        + port + "/api/rest/User.svc/user", request, UserDTO.class);
        assertNotNull(user);
        Long id = user.getId();
        User userSaved = userRepository.getOne(id);

        assertEquals("eliza", userSaved.getUsername());
        assertEquals("any@thing.com", userSaved.getEmail());
        assertEquals("Greece, Crete", userSaved.getAddress());
        assertEquals("101010", userSaved.getPersonalCode());

        userRepository.delete(userSaved);
    }

    @Test
    public void saveUserWithError() throws JsonProcessingException {
        UserDTO userDTOtoInsert = new UserDTO();
        userDTOtoInsert.setEmail("test@test.com");
        userDTOtoInsert.setAddress("somewhere far");
        userDTOtoInsert.setUsername("testing");

        HttpEntity<UserDTO> request =
                new HttpEntity<>(userDTOtoInsert);
        ErrorDTO userError = this.restTemplate.postForObject("http://localhost:"
                        + port + "/api/rest/User.svc/user", request,
                ErrorDTO.class);

        assertNotNull(userError);

        assertEquals("/api/rest/User.svc/user",
                userError.getPath());
        assertEquals("userDTO personal code can not be null ",
                userError.getMessage());
    }

    @Test
    void getAllUsers() {

    }

    @Test
    void getByFavouriteBook() {
        UserDTO userDTO = this.restTemplate.getForObject("http://localhost:"
                + port + "/api/rest/User.svc/user/favourite_book(Poem%20Strip)", UserDTO.class);
        assertEquals("user3", userDTO.getUsername());
    }

    @Test
    void getByBookName() {
        UserDTO userDTO = this.restTemplate.getForObject("http://localhost:"
                + port + "api/rest/User.svc/user/book(Poem%20Strip)", UserDTO.class);
        assertEquals("user3", userDTO.getUsername());
    }
}