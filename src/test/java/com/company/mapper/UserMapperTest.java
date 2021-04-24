package com.company.mapper;

import com.company.dto.UserDTO;
import com.company.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ActiveProfiles("test")
class UserMapperTest {

    private UserMapper userMapper = new UserMapper();

    @Test
    void fromDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("kikis");
        userDTO.setEmail("some@thing.com");
        userDTO.setPersonalCode("101010-10006");
        userDTO.setAddress("Riga, Latvia");
        userDTO.setFavouriteBook("Clean Code");

        User user = userMapper.fromDTO(userDTO);
        assertEquals(1L, user.getId());
        assertEquals("kikis", user.getUsername());
        assertEquals("some@thing.com", user.getEmail());
        assertEquals("101010-10006", user.getPersonalCode());
        assertEquals("Riga, Latvia", user.getAddress());
        assertEquals("Clean Code", user.getFavouriteBook());

    }

    @Test
    void toDTO() {
        User user = new User();
        user.setEmail("some@thing.com");
        user.setFavouriteBook("Clean Code");
        user.setAddress("Riga, Latvia");
        user.setPersonalCode("101010-10006");
        user.setId(1L);
        user.setUsername("kikis");

        UserDTO userDTO = userMapper.toDTO(user);
        assertEquals("some@thing.com", userDTO.getEmail());
        assertEquals("Clean Code", userDTO.getFavouriteBook());
        assertEquals("Riga, Latvia", userDTO.getAddress());
        assertEquals("101010-10006", userDTO.getPersonalCode());
        assertEquals(1L, userDTO.getId());
        assertEquals("kikis", userDTO.getUsername());
    }
}